package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.dto.DonacionDineroDTO;
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

  private LocalDateTime fechaDonacion;
  private double monto;
  private String frecuencia;
  private boolean formaPeriodica;

  public DonacionDinero(LocalDateTime fechaDonacion, double monto, String frecuencia, boolean formaPeriodica) {
    this.fechaDonacion = fechaDonacion;
    this.monto = monto;
    this.frecuencia = frecuencia;
    this.formaPeriodica = formaPeriodica;
  }

  public DonacionDinero() {
  }

  public DonacionDinero(DonacionDineroDTO donacionDineroDTO) {
    this.fechaDonacion = LocalDateTime.now();
    this.monto = donacionDineroDTO.getMonto();
    this.frecuencia = donacionDineroDTO.getFrecuencia();
    this.formaPeriodica = donacionDineroDTO.isFormaPeriodica();
  }
}
