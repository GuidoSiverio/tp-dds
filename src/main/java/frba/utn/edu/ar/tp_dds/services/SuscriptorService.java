package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
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

    private final TecnicoRepository tecnicoRepository;

    public SuscriptorService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public void agregarSuscriptor(Long heladeraId, Suscriptor suscriptor) {
        suscriptoresPorHeladera.computeIfAbsent(heladeraId, k -> new ArrayList<>()).add(suscriptor);
    }

    public void eliminarSuscriptor(Long heladeraId, Suscriptor suscriptor) {
        suscriptoresPorHeladera.get(heladeraId).remove(suscriptor);
        if (suscriptoresPorHeladera.get(heladeraId).isEmpty()) {
            suscriptoresPorHeladera.remove(heladeraId);
        }
    }

    public List<Suscriptor> obtenerSuscriptores(Long heladeraId) {
        return suscriptoresPorHeladera.getOrDefault(heladeraId, new ArrayList<>());
    }

    public void notificarSuscriptores(Long heladeraId, String mensaje) {
        List<Suscriptor> suscriptores = obtenerSuscriptores(heladeraId);
        for (Suscriptor suscriptor : suscriptores) {
            suscriptor.notificar(emailService, whatsappService, mensaje);
        }
        for (Suscriptor tecnico : tecnicoRepository.findAll()) {
            tecnico.notificar(emailService, whatsappService, mensaje);
        }
    }

    public List<Long> getSuscripciones(Long colaboradorId) {
        List<Long> suscripciones = new ArrayList<>();

        suscriptoresPorHeladera.forEach((heladeraId, suscriptores) -> {
            if (suscriptores.stream().anyMatch(suscriptor ->
                    suscriptor instanceof Colaborador && ((Colaborador) suscriptor).getId().equals(colaboradorId))) {
                suscripciones.add(heladeraId);
            }
        });
        return suscripciones;
    }

}

