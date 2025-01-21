package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    @Query("SELECT c FROM Colaborador c WHERE c.nroDoc = :nroDoc")
    Optional<Colaborador> findByNroDoc(String nroDoc);

    @Query(value = """
    SELECT 
        CASE 
            WHEN c.dtype = 'PersonaHumana' THEN CONCAT(c.nombre, ' - ', c.apellido)
            ELSE CONCAT(c.razon_social, ' - ', c.rubro)
        END AS colaborador, 
        COUNT(v.id) AS cantidadViandas
    FROM colaborador c
    LEFT JOIN vianda v ON c.id = v.colaborador_id
    GROUP BY c.id, c.dtype, c.nombre, c.apellido, c.razon_social, c.rubro
    """,
    nativeQuery = true)
    List<Object[]> viandasPorColaborador();
}
