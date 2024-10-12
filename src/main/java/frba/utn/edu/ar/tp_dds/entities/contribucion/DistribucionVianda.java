package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribucionVianda implements Contribucion{

  private Long id;
  private Heladera origen;
  private Heladera destino;
  private int cantidadViandas;
  private String motivoDistribucion;
  private LocalDateTime fechaDistribucion;

  public DistribucionVianda() {
  }

  @Override
  public void realizar() {

  }
}
