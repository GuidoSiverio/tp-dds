package frba.utn.edu.ar.tp_dds.entities.colaborador;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PersonaHumana")
@Getter
@Setter
public class PersonaHumana extends Colaborador{

  protected String nombre;
  protected String apellido;
  protected LocalDateTime fechaNacimiento;

}
