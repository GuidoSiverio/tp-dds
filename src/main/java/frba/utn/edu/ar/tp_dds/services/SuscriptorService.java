package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SuscriptorService {

    private final Map<Long, List<Suscriptor>> suscriptoresPorHeladera = new HashMap<>();

    @Autowired
    private EmailService emailService;

    @Autowired
    private WhatsAppService whatsappService;

    public void agregarSuscriptor(Long heladeraId, Suscriptor suscriptor) {
        suscriptoresPorHeladera.computeIfAbsent(heladeraId, k -> new ArrayList<>()).add(suscriptor);
    }

    public void eliminarSuscriptor(Long heladeraId, Suscriptor suscriptor) {
        suscriptoresPorHeladera.getOrDefault(heladeraId, new ArrayList<>()).remove(suscriptor);
    }

    public List<Suscriptor> obtenerSuscriptores(Long heladeraId) {
        return suscriptoresPorHeladera.getOrDefault(heladeraId, new ArrayList<>());
    }

    public void notificarSuscriptores(Long heladeraId, String mensaje) {
        List<Suscriptor> suscriptores = obtenerSuscriptores(heladeraId);
        for (Suscriptor suscriptor : suscriptores) {
            suscriptor.notificar(emailService, whatsappService, mensaje);
        }
    }
}

