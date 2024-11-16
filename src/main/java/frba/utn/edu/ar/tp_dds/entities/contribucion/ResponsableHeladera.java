package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ResponsableHeladera")
public class ResponsableHeladera extends Contribucion{

  @OneToOne
  private Heladera heladera;

  public void realizar() {

  }
}
