package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {

    @Query("SELECT COUNT(h) FROM Heladera h WHERE h.activa = true AND h.colaborador.id = ?1")
    Double getHeladerasActivas(Long id);

    @Query("SELECT COUNT(h) FROM Heladera h WHERE h.colaborador.id = ?1")
    Double getSumMesesActivas(Long id);
}
