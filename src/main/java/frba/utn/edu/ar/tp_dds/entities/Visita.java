package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.VisitaDTO;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaHoraVisita;
    private String comentarios;
    private String foto;
    private boolean estadoIncidente; // true = resuelto, false = no resuelto

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "incidente_id")
    private Incidente incidente;

    public Visita(VisitaDTO visitaDTO) {
        this.fechaHoraVisita = LocalDateTime.now();
        this.comentarios = visitaDTO.getComentario();
        this.estadoIncidente = visitaDTO.getSolucionado();
        this.foto = visitaDTO.getImagen();
    }

    public Visita() {
    }
}
