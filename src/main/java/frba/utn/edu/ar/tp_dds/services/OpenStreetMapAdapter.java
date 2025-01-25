package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.RecomendacionDTO;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;

@Service
public class OpenStreetMapAdapter implements RecomendacionService {

    private static final String NOMINATIM_SEARCH_URL = "https://nominatim.openstreetmap.org/search";

    @Override
    public List<RecomendacionDTO> obtenerRecomendaciones(double latitud, double longitud, double radio)
            throws IOException {

        String query = "restaurant";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        String url = NOMINATIM_SEARCH_URL +
                "?format=json" +
                "&q=" + encodedQuery +
                "&viewbox=" + getBoundingBox(latitud, longitud, radio) +
                "&bounded=1" +
                "&limit=10";

        // 'User-Agent' requerido por Nominatim
        String userAgent = "Tp-dds/1.0 (gsiverio@frba.utn.edu.ar)";

        String jsonResponse = Request.get(url)
                .addHeader("User-Agent", userAgent)
                .execute()
                .returnContent()
                .asString();

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> results = mapper.readValue(jsonResponse, List.class);

        List<RecomendacionDTO> recomendaciones = new ArrayList<>();

        for (Map<String, Object> result : results) {
            RecomendacionDTO recomendacion = new RecomendacionDTO();
            recomendacion.setNombre((String) result.get("display_name"));
            recomendacion.setLatitud(Double.parseDouble((String) result.get("lat")));
            recomendacion.setLongitud(Double.parseDouble((String) result.get("lon")));
            recomendaciones.add(recomendacion);
        }

        return recomendaciones;
    }

    private String getBoundingBox(double latitud, double longitud, double radio) {
        double radioEnGrados = radio / 111.32;
        double minLat = latitud - radioEnGrados;
        double maxLat = latitud + radioEnGrados;
        double minLon = longitud - radioEnGrados;
        double maxLon = longitud + radioEnGrados;

        return minLon + "," + maxLat + "," + maxLon + "," + minLat;
    }
}
