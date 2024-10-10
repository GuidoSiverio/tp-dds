package frba.utn.edu.ar.tp_dds.entities.colaborador;

import jakarta.persistence.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Colaborador {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String direccion;
  private String medioDeContacto;

  //@OneToMany
  //@JoinColumn(name = "colaborador_id")
  //private List<Contribucion> contribuciones;


}
