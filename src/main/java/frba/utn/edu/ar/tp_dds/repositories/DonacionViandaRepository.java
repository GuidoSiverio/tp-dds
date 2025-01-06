package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionVianda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionViandaRepository extends JpaRepository<DonacionVianda, Long> {

    @Query("SELECT COUNT(c) FROM Contribucion c WHERE TYPE(c) = 'DonacionVianda' AND c.colaborador.id = ?1")
    Double getViandasDonadas(Long id);
}
