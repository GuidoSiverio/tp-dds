package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.repositories.DonacionDineroRepository;
import org.springframework.stereotype.Service;

@Service
public class DonacionDineroService {

    private final DonacionDineroRepository donacionDineroRepository;

    public DonacionDineroService(DonacionDineroRepository donacionDineroRepository) {
        this.donacionDineroRepository = donacionDineroRepository;
    }

    public Double getPesosDonados(Long id) {
        return donacionDineroRepository.getPesosDonados(id);
    }
}
