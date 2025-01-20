package frba.utn.edu.ar.tp_dds.entities.incidente;

import frba.utn.edu.ar.tp_dds.entities.Visita;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL)
    private List<Visita> visitas = new ArrayList<>();

    @Transient
    private List<Suscriptor> suscriptores = new ArrayList<>();

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

    public void agregarObservador(Suscriptor suscriptor) {
        suscriptores.add(suscriptor);
    }

    public void notificar() {
        for (Suscriptor suscriptor : suscriptores) {
            suscriptor.notificar(this);
        }
    }
}
