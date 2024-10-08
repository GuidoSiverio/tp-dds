package frba.utn.edu.ar.tp_dds.bodies;

import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorBody {

  private String direccion;
  private String medioDeContacto;
  private PersonaHumana personaHumana;
  private PersonaJuridica personaJuridica;

}
