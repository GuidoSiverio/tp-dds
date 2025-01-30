package frba.utn.edu.ar.tp_dds.observer;

import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.services.EmailService;
import frba.utn.edu.ar.tp_dds.services.WhatsAppService;

public interface Suscriptor {

    void notificar(EmailService emailService, WhatsAppService whatsAppService, String mensaje);

    void notificar(Incidente incidente);
}
