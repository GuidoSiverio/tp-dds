package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.contribucion.RegistroPersonaVulnerable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroPersonaVulnerableRepository extends JpaRepository<RegistroPersonaVulnerable, Long> {
}
