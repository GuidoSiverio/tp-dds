package frba.utn.edu.ar.tp_dds.observer;

import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;

public interface Suscriptor {

    void notificar(String mensaje);

    void notificar(Incidente incidente);
}
