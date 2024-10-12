package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PersonaHumana")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaHumana extends Colaborador{

  protected String nombre;
  protected String apellido;
  protected LocalDateTime fechaNacimiento;

  public PersonaHumana() {
  }
}
