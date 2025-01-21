package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteFallasPorHeladera;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorHeladera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {

    @Query("SELECT COUNT(h) FROM Heladera h WHERE h.activa = true AND h.colaborador.id = ?1")
    Double getHeladerasActivas(Long id);

    @Query("SELECT COUNT(h) FROM Heladera h WHERE h.colaborador.id = ?1")
    Double getSumMesesActivas(Long id);

    @Query(value = "SELECT CONCAT(h.nombre, ' - ', h.direccion) AS heladera, count(i.id) AS cantidadFallas FROM heladera h \n" +
            "LEFT JOIN incidente i ON h.id = i.heladera_id WHERE i.dtype = 'Falla'\n" +
            "GROUP BY h.nombre, h.direccion;", nativeQuery = true)
    List<Object[]> fallasPorHeladera();

    @Query(value = "SELECT CONCAT(h.nombre, ' - ', h.direccion) AS heladera, count(v.id) AS cantidadViandas FROM heladera h \n" +
            "LEFT JOIN vianda v ON h.id = v.heladera_id\n" +
            "GROUP BY h.nombre, h.direccion;", nativeQuery = true)
    List<Object[]> viandasPorHeladera();
}
