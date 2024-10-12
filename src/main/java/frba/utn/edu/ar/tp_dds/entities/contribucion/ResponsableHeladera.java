package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsableHeladera implements Contribucion{

  private Long id;
  private boolean tieneHeladera;
  private Heladera heladera;

  @Override
  public void realizar() {

  }
}
