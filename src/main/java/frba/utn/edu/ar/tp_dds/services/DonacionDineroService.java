package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.DonacionDineroDTO;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionDinero;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.DonacionDineroRepository;
import org.springframework.stereotype.Service;

@Service
public class DonacionDineroService {

    private final DonacionDineroRepository donacionDineroRepository;
    private final ColaboradorRepository colaboradorRepository;

    public DonacionDineroService(DonacionDineroRepository donacionDineroRepository, ColaboradorRepository colaboradorRepository) {
        this.donacionDineroRepository = donacionDineroRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void save(DonacionDinero donacionDinero) {
        donacionDineroRepository.save(donacionDinero);
    }

    public Double getPesosDonados(Long id) {
        return donacionDineroRepository.getPesosDonados(id);
    }

    public void donarDinero(DonacionDineroDTO donacionDineroDTO) {

        colaboradorRepository.findById(donacionDineroDTO.getColaboradorId()).ifPresent(colaborador -> {
            DonacionDinero donacionDinero = new DonacionDinero(donacionDineroDTO);
            colaborador.add(donacionDinero);
            save(donacionDinero);
        });
    }
}
