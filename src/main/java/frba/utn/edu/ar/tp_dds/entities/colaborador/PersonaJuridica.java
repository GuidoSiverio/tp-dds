package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PersonaJuridica")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaJuridica extends Colaborador{

  protected String razonSocial;
  protected String tipo;
  protected String rubro;

  public PersonaJuridica() {
  }
}
