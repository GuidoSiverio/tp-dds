package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.TarjetaDTO;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final ColaboradorRepository colaboradorRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository, ColaboradorRepository colaboradorRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void save(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
    }

    public void save(TarjetaDTO tarjetaDTO) {
        Tarjeta tarjeta = new Tarjeta(tarjetaDTO);
        colaboradorRepository.findById(tarjetaDTO.getColaboradorId()).ifPresent(colaborador -> {
            colaborador.add(tarjeta);
        });
        save(tarjeta);
    }

    public List<Tarjeta> findTarjetasDisponibles() {
        return tarjetaRepository.findAll().stream().filter(tarjeta -> !tarjeta.isAsignada()).toList();
    }

    public List<Tarjeta> findTarjeta(String codigo) {
        return tarjetaRepository.findTarjeta(codigo);
    }

    public Double getTarjetasRepartidas(Long id) {
        return tarjetaRepository.getTarjetasRepartidas(id);
    }

    public List<Tarjeta> findAll() {
        return tarjetaRepository.findAll();
    }
}
