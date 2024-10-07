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

@Entity
@Getter
@Setter
public class DistribucionVianda implements Contribucion{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private Heladera origen;
  @OneToOne
  private Heladera destino;
  private int cantidadViandas;
  private String motivoDistribucion;
  private LocalDateTime fechaDistribucion;
  @Override
  public void realizar() {

  }
}
