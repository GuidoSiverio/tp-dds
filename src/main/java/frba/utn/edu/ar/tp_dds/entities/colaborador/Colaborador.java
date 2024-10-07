package frba.utn.edu.ar.tp_dds.entities.colaborador;

import frba.utn.edu.ar.tp_dds.entities.contribucion.Contribucion;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
