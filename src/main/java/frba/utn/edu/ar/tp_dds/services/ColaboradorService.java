package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import frba.utn.edu.ar.tp_dds.repositories.ColabordaorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    private final ColabordaorRepository colaboradorRepository;

    public ColaboradorService(ColabordaorRepository colaboradorRepository) {
      this.colaboradorRepository = colaboradorRepository;
    }

    public void save(ColaboradorDTO colaboradorDTO) {
        if (colaboradorDTO.getNombre() != null){
            Colaborador personaHumana = new PersonaHumana(colaboradorDTO);
            save(personaHumana);
        } else if (colaboradorDTO.getRazonSocial() != null){
            Colaborador personaJuridica = new PersonaJuridica(colaboradorDTO);
            save(personaJuridica);
        }
    }

    public void save(Colaborador colaborador) {
        colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> findAll() {
    return colaboradorRepository.findAll();
    }

    public void delete(Long id) {
        colaboradorRepository.deleteById(id);
    }

    public Optional<Colaborador> findById(Long id) {
        return colaboradorRepository.findById(id);
    }

    public void update(Long id, ColaboradorDTO colaboradorDTO) {
        colaboradorRepository.findById(id).ifPresentOrElse(colaborador -> {
            if (colaboradorDTO.getDireccion() != null) {
                colaborador.setDireccion(colaboradorDTO.getDireccion());
            }
            if (colaboradorDTO.getMedioDeContacto() != null) {
                colaborador.setMedioDeContacto(colaboradorDTO.getMedioDeContacto());
            }

            if (colaborador instanceof PersonaHumana personaHumana) {
                if (colaboradorDTO.getNombre() != null) {
                    personaHumana.setNombre(colaboradorDTO.getNombre());
                }
                if (colaboradorDTO.getApellido() != null) {
                    personaHumana.setApellido(colaboradorDTO.getApellido());
                }
                if (colaboradorDTO.getFechaDeNacimiento() != null) {
                    personaHumana.setFechaNacimiento(LocalDateTime.parse(colaboradorDTO.getFechaDeNacimiento()));
                }
            } else if (colaborador instanceof PersonaJuridica personaJuridica) {
                if (colaboradorDTO.getRazonSocial() != null) {
                    personaJuridica.setRazonSocial(colaboradorDTO.getRazonSocial());
                }
                if (colaboradorDTO.getRubro() != null) {
                    personaJuridica.setRubro(colaboradorDTO.getRubro());
                }
                if (colaboradorDTO.getTipo() != null) {
                    personaJuridica.setTipo(colaboradorDTO.getTipo());
                }
            }
            save(colaborador);
        }, () -> {
            throw new RuntimeException("Colaborador no encontrado con id: " + id);
        });
    }

    private boolean isNullOrEmpty(String campo) {
        return campo == null || campo.isEmpty();
    }

}
