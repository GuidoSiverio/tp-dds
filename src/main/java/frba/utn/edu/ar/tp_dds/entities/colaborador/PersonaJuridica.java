package frba.utn.edu.ar.tp_dds.entities.colaborador;

import jakarta.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PersonaJuridica")
@Getter
@Setter
public class PersonaJuridica extends Colaborador{

  protected String razonSocial;
  protected String tipo;
  protected String rubro;

}
