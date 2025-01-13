package frba.utn.edu.ar.tp_dds.entities.heladera;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
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

  @ManyToOne
  @JoinColumn(name = "colaborador_id", insertable = false, updatable = false)
  private Colaborador colaborador;

  @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL)
  private List<Incidente> incidentes;

  public void ingresarVianda(Vianda vianda) {
    viandas.add(vianda);
  }

  public void registrar(Incidente incidente){
    this.incidentes.add(incidente);
    incidente.setHeladera(this);
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

  public boolean estaActiva() {
    return activa;
  }
}
