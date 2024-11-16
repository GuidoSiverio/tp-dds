package frba.utn.edu.ar.tp_dds.entities.heladera;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Heladera {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String longitud;
  private String latitud;
  private String direccion;
  private String nombre;
  private int capacidad;
  private LocalDateTime fechaFuncionamiento;
  @OneToMany
  @JoinColumn(name = "heladera_id")
  private List<Vianda> viandas;
  private boolean activa;
  private Double tempMinAceptable;
  private Double tempMaxAceptable;
  private Double ultimaTemp;

  public void ingresarVianda(Vianda vianda) {
    viandas.add(vianda);
  }

  public Heladera(HeladeraDTO heladeraDTO) {
    this.longitud = heladeraDTO.getLongitud();
    this.latitud = heladeraDTO.getLatitud();
    this.direccion = heladeraDTO.getDireccion();
    this.nombre = heladeraDTO.getNombre();
    this.capacidad = heladeraDTO.getCapacidad();
    this.fechaFuncionamiento = LocalDateTime.parse(heladeraDTO.getFechaFuncionamiento());
    this.viandas = new ArrayList<>();
  }

  public Heladera() {
  }
}
