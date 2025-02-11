package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.Visita;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final UserService userService;
    private final VisitaService visitaService;

    public TecnicoService(TecnicoRepository tecnicoRepository, UserService userService, SuscriptorService suscriptorService, VisitaService visitaService) {
        this.tecnicoRepository = tecnicoRepository;
        this.userService = userService;
        this.visitaService = visitaService;
    }

    public void save(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        save(tecnico);
        User user = new User(tecnicoDTO.getUser(), tecnicoDTO.getPassword(), "TECNICO", tecnico);
        userService.save(user);
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
        userService.deleteByTecnicoId(id);
        visitaService.deleteVisitsByTecnicoId(id);
        tecnicoRepository.deleteById(id);
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
