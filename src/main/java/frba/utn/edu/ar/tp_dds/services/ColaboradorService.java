package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ColaboradorCsvDTO;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import frba.utn.edu.ar.tp_dds.repositories.ColabordaorRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ColaboradorService {

    private final ColabordaorRepository colaboradorRepository;

    public ColaboradorService(ColabordaorRepository colaboradorRepository) {
      this.colaboradorRepository = colaboradorRepository;
    }

    public Colaborador save(ColaboradorDTO colaboradorDTO) {
        if (colaboradorDTO.getNombre() != null){
            Colaborador personaHumana = new PersonaHumana(colaboradorDTO);
            save(personaHumana);
            return personaHumana;
        } else if (colaboradorDTO.getRazonSocial() != null){
            Colaborador personaJuridica = new PersonaJuridica(colaboradorDTO);
            save(personaJuridica);
            return personaJuridica;
        }
        throw new RuntimeException("No se pudo crear el colaborador");
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

    public void saveAllFromCsv(MultipartFile file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<ColaboradorCsvDTO> colaboradorCsvDTOs = reader.lines()
                    .skip(1) // Skip header
                    .map(this::parseCsvLineToColaboradorCsvDTO)
                    .toList();

            List<Colaborador> existingColaboradores = new ArrayList<>();
            List<Colaborador> newColaboradores = new ArrayList<>();

            colaboradorCsvDTOs.forEach(colab -> {
                if (findByNroDoc(colab.getNroDoc()).isPresent()) {
                    existingColaboradores.add(agregarContribuciones(colab));
                } else {
                    newColaboradores.add(parseCsvLineToColaborador(colab));
                }
            });

        }
    }

    private Optional<Object> findByNroDoc(String nroDoc) {
        return colaboradorRepository.findByNroDoc(nroDoc);
    }

    private ColaboradorCsvDTO parseCsvLineToColaboradorCsvDTO(String line) {
        List<String> fields = List.of(line.split(","));
        return new ColaboradorCsvDTO(fields);
    }


    private Colaborador parseCsvLineToColaborador(ColaboradorCsvDTO colab) {
        return new PersonaHumana(colab);
    }

    private Colaborador agregarContribuciones(ColaboradorCsvDTO colab) {
        return null;
    }

    public Colaborador generate(ColaboradorDTO colaboradorDTO) {
        if (colaboradorDTO.getNombre() != null){
            return new PersonaHumana(colaboradorDTO);
        } else if (colaboradorDTO.getRazonSocial() != null){
            return new PersonaJuridica(colaboradorDTO);
        }
        throw new RuntimeException("No se pudo crear el colaborador");
    }
}
