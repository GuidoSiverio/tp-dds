package frba.utn.edu.ar.tp_dds.entities.tarjeta;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RegistroUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Heladera heladeraUsada;
    private LocalDateTime fechaUso;

}
