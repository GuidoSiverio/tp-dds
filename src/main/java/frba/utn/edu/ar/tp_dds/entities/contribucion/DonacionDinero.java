package frba.utn.edu.ar.tp_dds.entities.contribucion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonacionDinero implements Contribucion{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime fechaDonacion;

  private double monto;

  private int frecuencia;

  private boolean formaPeriodica;

  @Override
  public void realizar() {

  }
}
