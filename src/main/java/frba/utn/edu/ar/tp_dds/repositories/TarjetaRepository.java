package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

    @Query("SELECT COUNT(t) FROM Tarjeta t WHERE t.colaborador.id = ?1")
    Double getTarjetasRepartidas(Long id);

    @Query("SELECT t FROM Tarjeta t WHERE t.codigo = ?1 AND t.asignada = false")
    List<Tarjeta> findTarjeta(String codigo);
}
