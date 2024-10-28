package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.DistribucionViandaDTO;
import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContribucionService {

    private final HeladeraService heladeraService;
    private final ViandaService viandaService;
    private final ColaboradorService colaboradorService;
    private final TarjetaService tarjetaService;
    private final PersonaVulnerableService personaVulnerableService;

    public ContribucionService(HeladeraService heladeraService, ViandaService viandaService, ColaboradorService colaboradorService, TarjetaService tarjetaService, PersonaVulnerableService personaVulnerableService) {
        this.heladeraService = heladeraService;
        this.viandaService = viandaService;
        this.colaboradorService = colaboradorService;
        this.tarjetaService = tarjetaService;
        this.personaVulnerableService = personaVulnerableService;
    }

    public void distribuirViandas(DistribucionViandaDTO distribucionViandaDTO){

        Heladera heladeraOrigen = heladeraService.findById(distribucionViandaDTO.getOrigenId()).orElseThrow(() -> new RuntimeException("No se encontró la heladera de origen"));
        Heladera heladeraDestino = heladeraService.findById(distribucionViandaDTO.getDestinoId()).orElseThrow(() -> new RuntimeException("No se encontró la heladera de destino"));

        List<Vianda> viandasADistribuir = heladeraOrigen.getViandas().subList(0, distribucionViandaDTO.getCantidadViandas());
        viandasADistribuir.forEach(vianda -> {
            heladeraOrigen.getViandas().remove(vianda);
            heladeraDestino.getViandas().add(vianda);
        });

        heladeraService.save(heladeraOrigen);
        heladeraService.save(heladeraDestino);
    }

    public void validarEspacioHeladeras(Long origenId, Long destinoId, int cantViandasADistribuir) {
        heladeraService.findById(origenId).ifPresent(origen -> {
            if (origen.getViandas().size() < cantViandasADistribuir) {
                throw new RuntimeException("No hay viandas disponibles en la heladera de origen");
            }
        });
        heladeraService.findById(destinoId).ifPresent(destino -> {
            if (destino.getCapacidad() - destino.getViandas().size() < cantViandasADistribuir) {
                throw new RuntimeException("No hay espacio suficiente en la heladera de destino");
            }
        });
    }

    public void donarVianda(ViandaDTO viandaDTO) {
        Vianda vianda = new Vianda(viandaDTO);
        colaboradorService.findById(viandaDTO.getColaboradorId()).ifPresentOrElse(vianda::setColaborador, () -> {
            throw new RuntimeException("No se encontró el colaborador");
        });
        vianda.setFechaDonacion(LocalDateTime.now());
        heladeraService.findById(viandaDTO.getHeladeraId()).ifPresent(heladera -> {
            heladera.ingresarVianda(vianda);
        });
        viandaService.save(vianda);
    }

    public void registrarPersonaVulnerable(PersonaVulnerableDTO personaVulnerableDTO) {
        List<Tarjeta> tarjetas = tarjetaService.findTarjetasDisponibles();

        tarjetas.stream().findFirst().ifPresentOrElse(tarjeta -> {
            PersonaVulnerable personaVulnerable = new PersonaVulnerable(personaVulnerableDTO);
            personaVulnerable.setTarjeta(tarjeta);
            tarjeta.registrarAsignacion();
            personaVulnerableService.save(personaVulnerable);
            tarjetaService.save(tarjeta);
        }, () -> {
            throw new RuntimeException("No hay tarjetas disponibles");
        });
    }

    public Double getViandasDistribuidas(Long id) {
        return 0.0;
    }

    public Double getTarjetasRepartidas(Long id) {
        return 0.0;
    }
}
