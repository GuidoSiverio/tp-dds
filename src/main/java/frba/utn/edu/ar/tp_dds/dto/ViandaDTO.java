package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViandaDTO {

  private String comida;
  private String fechaCaducidad;
  private double calorias;
  private double peso;
  private Long colaboradorId;
  private Long heladeraId;

}
