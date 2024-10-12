package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Colaborador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String direccion;
  private String medioDeContacto;

  //@OneToMany
  //@JoinColumn(name = "colaborador_id")
  //private List<Contribucion> contribuciones;


  public Colaborador() {
  }
}
