package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.DistribucionViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DistribucionVianda;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.DistribucionViandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistribucionViandaService {

    private final HeladeraService heladeraService;
    private final ColaboradorRepository colabordaorRepository;
    private final DistribucionViandaRepository distribucionViandaRepository;

    public DistribucionViandaService(HeladeraService heladeraService, ColaboradorRepository colabordaorRepository, DistribucionViandaRepository distribucionViandaRepository) {
        this.heladeraService = heladeraService;
        this.colabordaorRepository = colabordaorRepository;
        this.distribucionViandaRepository = distribucionViandaRepository;
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

        DistribucionVianda distribucionVianda = new DistribucionVianda(distribucionViandaDTO);
        save(distribucionVianda);

        colabordaorRepository.findById(distribucionViandaDTO.getColaboradorId()).ifPresent(colaborador -> {
            colaborador.add(distribucionVianda);
        });
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

    public void save(DistribucionVianda distribucionVianda) {
        distribucionViandaRepository.save(distribucionVianda);
    }

    public Double getViandasDistribuidas(Long id) {
        return distribucionViandaRepository.getViandasDistribuidas(id);
    }
}
