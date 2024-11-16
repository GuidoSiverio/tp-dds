package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionDinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionDineroRepository extends JpaRepository<DonacionDinero, Long> {
}
