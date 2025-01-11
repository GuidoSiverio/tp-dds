package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Contribucion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

}
