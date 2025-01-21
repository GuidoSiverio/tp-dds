package frba.utn.edu.ar.tp_dds.entities.tarjeta;

import frba.utn.edu.ar.tp_dds.dto.TarjetaDTO;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
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

    @OneToOne
    private Colaborador colaborador;


    public Tarjeta(TarjetaDTO tarjetaDTO) {
        this.codigo = tarjetaDTO.getCodigo();
        this.asignada = tarjetaDTO.isAsignada();
    }

    public Tarjeta() {
    }

    public void registrarAsignacion() {
        this.asignada = true;
    }

}
