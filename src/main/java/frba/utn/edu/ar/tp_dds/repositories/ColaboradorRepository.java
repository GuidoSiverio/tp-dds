package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    @Query("SELECT c FROM Colaborador c WHERE c.nroDoc = :nroDoc")
    Optional<Colaborador> findByNroDoc(String nroDoc);
}
