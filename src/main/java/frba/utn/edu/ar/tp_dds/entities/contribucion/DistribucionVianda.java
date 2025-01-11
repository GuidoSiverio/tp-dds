package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.dto.DistribucionViandaDTO;
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

  public DistribucionVianda(DistribucionViandaDTO distribucionViandaDTO) {
    this.origenId = distribucionViandaDTO.getHeladeraOrigen();
    this.destinoId = distribucionViandaDTO.getHeladeraDestino();
    this.cantidadViandas = distribucionViandaDTO.getCantidadViandas();
    this.motivoDistribucion = distribucionViandaDTO.getMotivoDistribucion();
    this.fechaDistribucion = distribucionViandaDTO.getFechaDistribucion();
  }


  public DistribucionVianda() {
  }

  public void realizar() {

  }
}
