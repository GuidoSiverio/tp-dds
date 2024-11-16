package frba.utn.edu.ar.tp_dds.entities.tarjeta;

import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Tarjeta {

    @Id
    private String codigo;
    private boolean asignada;
    @OneToOne
    private PersonaVulnerable personaVulnerable;
    @OneToMany
    @JoinColumn(name = "tarjeta_id")
    private List<RegistroUso> registroUsos;

    public void registrarAsignacion() {
        this.asignada = true;
    }

}
