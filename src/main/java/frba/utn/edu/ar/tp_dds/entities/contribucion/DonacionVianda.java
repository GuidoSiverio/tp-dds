package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Vianda;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DonacionVianda")
public class DonacionVianda extends Contribucion{

  //private List<Vianda> viandas;

  public void realizar() {

  }
}
