package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}