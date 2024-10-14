package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Heladera;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribucionVianda implements Contribucion{

  private Long origenId;
  private Long destinoId;
  private int cantidadViandas;
  private String motivoDistribucion;
  private LocalDateTime fechaDistribucion;

  public DistribucionVianda() {
  }

  @Override
  public void realizar() {

  }
}
