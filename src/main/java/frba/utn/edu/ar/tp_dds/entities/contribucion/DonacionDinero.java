package frba.utn.edu.ar.tp_dds.entities.contribucion;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DonacionDinero")
public class DonacionDinero extends Contribucion{

  private Long id;
  private LocalDateTime fechaDonacion;
  private double monto;
  private int frecuencia;
  private boolean formaPeriodica;

  public void realizar() {

  }
}
