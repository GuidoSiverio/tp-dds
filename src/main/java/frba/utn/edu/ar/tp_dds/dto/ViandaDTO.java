package frba.utn.edu.ar.tp_dds.dto;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViandaDTO {

  private String comida;
  private LocalDateTime fechaCaducidad;
  private double calorias;
  private double peso;
  private Long colaboradorId;
  private Long heladeraId;

}
