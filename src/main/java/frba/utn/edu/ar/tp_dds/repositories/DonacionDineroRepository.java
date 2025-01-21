package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionDineroRepository extends JpaRepository<DonacionDinero, Long> {

    @Query("SELECT SUM(monto) FROM Contribucion c WHERE TYPE(c) = 'DonacionDinero' AND c.colaborador.id = ?1")
    Double getPesosDonados(Long id);
}
