package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.contribucion.DistribucionVianda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribucionViandaRepository extends JpaRepository<DistribucionVianda, Long> {

    @Query("SELECT COUNT(c) FROM Contribucion c WHERE TYPE(c) = 'DistribucionVianda' AND c.colaborador.id = ?1")
    Double getViandasDistribuidas(Long id);
}
