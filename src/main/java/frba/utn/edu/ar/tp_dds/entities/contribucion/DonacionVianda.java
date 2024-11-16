package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Vianda;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DonacionVianda")
public class DonacionVianda extends Contribucion{

  @OneToOne
  private Vianda vianda;

  public DonacionVianda(Vianda vianda) {
    this.vianda = vianda;
  }

  public DonacionVianda() {
  }

  public void realizar() {

  }
}
