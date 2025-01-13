package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Tecnico tecnico;

    @ManyToOne
    private Incidente incidente;
}
