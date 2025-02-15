package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
import frba.utn.edu.ar.tp_dds.repositories.UserRepository;
import frba.utn.edu.ar.tp_dds.repositories.ViandaRepository;
import frba.utn.edu.ar.tp_dds.repositories.VisitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final UserService userService;
    private final VisitaRepository visitaRepository;
    private final UserRepository userRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, UserService userService, SuscriptorService suscriptorService, VisitaRepository visitaRepository, UserRepository userRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.userService = userService;
        this.visitaRepository = visitaRepository;
        this.userRepository = userRepository;
    }

    public void save(TecnicoDTO tecnicoDTO) {
        User user = new User(tecnicoDTO.getUser(), tecnicoDTO.getPassword(), "TECNICO");
        userService.save(user);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        tecnico.setUser(user);
        save(tecnico);
    }

    public void save(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    public void update(Long id, TecnicoDTO tecnicoDTO) {
        tecnicoRepository.findById(id).ifPresentOrElse(t -> {
            t.update(tecnicoDTO);
            save(t);
        }, () -> {
            throw new RuntimeException("Tecnico no encontrado");
        });
    }

    public void delete(Long id) {
        visitaRepository.deleteByTecnicoId(id);
        Tecnico tecnico = tecnicoRepository.findById(id).orElse(null);
        tecnicoRepository.deleteById(id);
        if (tecnico != null) {
            userRepository.delete(tecnico.getUser());
        }
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
