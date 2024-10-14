package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DistribucionVianda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContribucionService {

    private final HeladeraService heladeraService;
    private final ViandaService viandaService;
    private final ColaboradorService colaboradorService;

    public ContribucionService(HeladeraService heladeraService, ViandaService viandaService, ColaboradorService colaboradorService) {
        this.heladeraService = heladeraService;
        this.viandaService = viandaService;
        this.colaboradorService = colaboradorService;
    }

    public void distribuirViandas(DistribucionVianda distribucionVianda){

        Heladera heladeraOrigen = heladeraService.findById(distribucionVianda.getOrigenId()).orElseThrow(() -> new RuntimeException("No se encontró la heladera de origen"));
        Heladera heladeraDestino = heladeraService.findById(distribucionVianda.getDestinoId()).orElseThrow(() -> new RuntimeException("No se encontró la heladera de destino"));

        List<Vianda> viandasADistribuir = heladeraOrigen.getViandas().subList(0, distribucionVianda.getCantidadViandas());
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


}
