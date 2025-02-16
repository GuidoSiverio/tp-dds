package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.RecomendacionDTO;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;

import java.io.IOException;
import java.util.List;

public interface RecomendacionService {
    List<RecomendacionDTO> obtenerRecomendaciones(double latitud, double longitud, double radio) throws IOException;
}
