package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {
}
