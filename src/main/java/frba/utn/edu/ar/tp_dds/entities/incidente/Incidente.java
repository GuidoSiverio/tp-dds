package frba.utn.edu.ar.tp_dds.entities.incidente;

import frba.utn.edu.ar.tp_dds.entities.Visita;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaHora;
    private boolean estadoResuelta; // true = resuelto, false = no resuelto

    @ManyToOne
    private Heladera heladera;

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL)
    private List<Visita> visitas = new ArrayList<>();

    public Incidente(LocalDateTime fechaHora, boolean estado) {
        this.fechaHora = fechaHora;
        this.estadoResuelta = estado;
    }

    public Incidente() {
    }

    public void add(Visita visita){
        this.visitas.add(visita);
        visita.setIncidente(this);
    }
}
