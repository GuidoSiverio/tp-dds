package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vianda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String comida;
  private LocalDateTime fechaCaducidad;
  private LocalDateTime fechaDonacion;
  private double calorias;
  private double peso;
  private LocalDateTime fechaEntrega;
  private boolean fueEntregada;

  public Vianda(ViandaDTO viandaDTO) {
    this.comida = viandaDTO.getComida();
    this.fechaCaducidad = LocalDateTime.parse(viandaDTO.getFechaCaducidad() + "T00:00:00");
    this.calorias = viandaDTO.getCalorias();
    this.peso = viandaDTO.getPeso();
  }

  public Vianda() {
  }
}
