package frba.utn.edu.ar.tp_dds.entities.contribucion;

import jakarta.persistence.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Contribucion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
