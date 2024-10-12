package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DistribucionVianda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        List<Vianda> viandasADistribuir = distribucionVianda.getOrigen().getViandas().subList(0, distribucionVianda.getCantidadViandas()-1);
        viandasADistribuir.forEach(vianda -> {
            distribucionVianda.getOrigen().getViandas().remove(vianda);
            distribucionVianda.getDestino().getViandas().add(vianda);
        });

        heladeraService.save(distribucionVianda.getOrigen());
        heladeraService.save(distribucionVianda.getDestino());

        distribucionVianda.setFechaDistribucion(LocalDateTime.now());
    }

    public void validarEspacioHeladeras(Heladera origen, Heladera destino, int cantViandasADistribuir) {
        if (origen.getViandas().size() < cantViandasADistribuir) {
            throw new RuntimeException("No hay viandas disponibles en la heladera de origen");
        }
        if (destino.getCapacidad() - destino.getViandas().size() < cantViandasADistribuir) {
            throw new RuntimeException("No hay espacio suficiente en la heladera de destino");
        }
    }

    public void donarVianda(ViandaDTO viandaDTO) {
        Vianda vianda = new Vianda(viandaDTO);
        colaboradorService.findById(viandaDTO.getColaboradorId()).ifPresent(vianda::setColaborador);
        vianda.setFechaDonacion(LocalDateTime.now());
        heladeraService.findById(viandaDTO.getHeladeraId()).ifPresent(heladera -> {
            heladera.ingresarVianda(vianda);
            vianda.setHeladera(heladera);
            heladeraService.save(heladera);
        });
        viandaService.save(vianda);
    }


}
