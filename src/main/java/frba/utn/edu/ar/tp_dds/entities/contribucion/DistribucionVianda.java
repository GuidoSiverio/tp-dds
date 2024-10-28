package frba.utn.edu.ar.tp_dds.entities.contribucion;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DistribucionVianda")
public class DistribucionVianda extends Contribucion{

  private Long origenId;
  private Long destinoId;
  private int cantidadViandas;
  private String motivoDistribucion;
  private LocalDateTime fechaDistribucion;

  public DistribucionVianda() {
  }

  public void realizar() {

  }
}
