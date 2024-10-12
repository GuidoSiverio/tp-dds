package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Vianda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonacionVianda implements Contribucion{

  private Long id;
  private List<Vianda> viandas;

  @Override
  public void realizar() {

  }
}
