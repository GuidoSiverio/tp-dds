package frba.utn.edu.ar.tp_dds.observer;

import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;

public interface Suscriptor {

    void notificar(Incidente incidente);

    void notificar(String mensaje);
}
