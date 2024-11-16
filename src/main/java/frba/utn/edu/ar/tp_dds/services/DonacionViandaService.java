package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionVianda;
import frba.utn.edu.ar.tp_dds.repositories.DonacionViandaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DonacionViandaService {

    private final ColaboradorService colaboradorService;
    private final HeladeraService heladeraService;
    private final ViandaService viandaService;
    private final DonacionViandaRepository donacionViandaRepository;

    public DonacionViandaService(ColaboradorService colaboradorService, HeladeraService heladeraService, ViandaService viandaService, DonacionViandaRepository donacionViandaRepository) {
        this.colaboradorService = colaboradorService;
        this.heladeraService = heladeraService;
        this.viandaService = viandaService;
        this.donacionViandaRepository = donacionViandaRepository;
    }

    public void donarVianda(ViandaDTO viandaDTO) {
        Vianda vianda = new Vianda(viandaDTO);
        vianda.setFechaDonacion(LocalDateTime.now());
        heladeraService.findById(viandaDTO.getHeladeraId()).ifPresent(heladera -> {
            heladera.ingresarVianda(vianda);
        });
        viandaService.save(vianda);

        DonacionVianda donacionVianda = new DonacionVianda(vianda);
        save(donacionVianda);
        colaboradorService.findById(viandaDTO.getColaboradorId()).ifPresent(colaborador -> {
            colaborador.add(donacionVianda);
            colaboradorService.save(colaborador);
        });
    }

    public void save(DonacionVianda donacionVianda) {
        donacionViandaRepository.save(donacionVianda);
    }
}
