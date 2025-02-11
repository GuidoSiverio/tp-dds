package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionDonacionService {

    @Autowired
    private HeladeraRepository heladeraRepository;

    public List<Heladera> recomendarDonaciones(String lonUsuario, String latUsuario) {
        List<Heladera> heladeras = heladeraRepository.findAll().stream().filter(Heladera::isActiva).toList();

        return heladeras.stream()
                .filter(this::tieneEspacioDisponible)
                .sorted(Comparator.comparingDouble(h -> calcularDistancia(Double.parseDouble(latUsuario), Double.parseDouble(lonUsuario), Double.parseDouble(h.getLatitud()), Double.parseDouble(h.getLongitud()))))
                .collect(Collectors.toList());
    }

    private boolean tieneEspacioDisponible(Heladera heladera) {
        return heladera.getCapacidad() > heladera.getViandas().size();
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int RADIO_TIERRA_KM = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA_KM * c;
    }
}
