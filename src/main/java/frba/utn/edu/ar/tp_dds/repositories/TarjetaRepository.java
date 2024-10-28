package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}
