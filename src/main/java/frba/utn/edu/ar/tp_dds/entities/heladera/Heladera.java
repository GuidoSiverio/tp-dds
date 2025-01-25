package frba.utn.edu.ar.tp_dds.entities.heladera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

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

  @Transient
  private List<Suscriptor> suscriptores = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Incidente> incidentes;

  @Transient
  private AmqpTemplate amqpTemplate;

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

  public void removerSuscripcion(Suscriptor suscriptor) {
    suscriptores.remove(suscriptor);
  }

  public void notificarEvento(String mensaje) {
    for (Suscriptor suscriptor : suscriptores) {
      suscriptor.notificar(mensaje);
    }
  }

  public void verificarCondicion(int viandasRestantesParaNotificar, int viandasFaltantesParaLlenar, boolean notificarDesperfectos) {
    if (viandasRestantesParaNotificar > 0 && getViandas().size() <= viandasRestantesParaNotificar) {
      notificarEvento("Quedan pocas viandas en la heladera: " + nombre);
    }
    if (viandasFaltantesParaLlenar > 0 && (getCapacidad() - getViandas().size()) <= viandasFaltantesParaLlenar) {
      notificarEvento("Faltan pocas viandas para llenar la heladera: " + nombre);
    }
    if (notificarDesperfectos && heladeraSufrioDesperfecto()) {
      notificarEvento("La heladera " + nombre + " sufrió un desperfecto.");
    }
  }

  private boolean heladeraSufrioDesperfecto() {
    // Implementa la lógica para verificar desperfectos.
    return false;
  }

  public void enviarTemperatura(double temperatura) {
    String mensaje = "Temperatura actual: " + temperatura;
    amqpTemplate.convertAndSend("temperaturas", mensaje);
  }

  public void enviarAlerta(String mensaje) {
    amqpTemplate.convertAndSend("alertas", mensaje);
  }

  public void autorizarApertura(Long colaboradorId) {
    String mensaje = "Autorización para apertura por colaborador: " + colaboradorId;
    amqpTemplate.convertAndSend("autorizaciones", mensaje);
  }

  public void add(Suscriptor suscriptor){
    suscriptores.add(suscriptor);
  }
}
