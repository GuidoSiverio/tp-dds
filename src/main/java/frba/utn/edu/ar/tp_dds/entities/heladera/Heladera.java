package frba.utn.edu.ar.tp_dds.entities.heladera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.services.SuscriptorService;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Incidente> incidentes;

  public void ingresarVianda(Vianda vianda) {
    viandas.add(vianda);
  }

  public void registrar(Incidente incidente){
    this.incidentes.add(incidente);
    incidente.setHeladera(this);
    this.setActiva(false);
  }

  public Heladera(HeladeraDTO heladeraDTO) {
    this.longitud = heladeraDTO.getLongitud();
    this.latitud = heladeraDTO.getLatitud();
    this.direccion = heladeraDTO.getDireccion();
    this.nombre = heladeraDTO.getNombre();
    this.capacidad = heladeraDTO.getCapacidad();
    this.tempMinAceptable = Double.parseDouble(heladeraDTO.getTempMinAceptable());
    this.tempMaxAceptable = Double.parseDouble(heladeraDTO.getTempMaxAceptable());
    this.fechaFuncionamiento = LocalDateTime.parse(heladeraDTO.getFechaFuncionamiento());
    this.viandas = new ArrayList<>();
    this.activa = true;
  }

  public Heladera() {
  }

  public boolean estaActiva() {
    return activa;
  }

  public void notificarEvento(SuscriptorService suscriptorService, String mensaje) {
    if (suscriptorService != null) {
      suscriptorService.notificarSuscriptores(this.id, mensaje);
    }
  }

//  public void verificarCondicion(int viandasRestantesParaNotificar, int viandasFaltantesParaLlenar, boolean notificarDesperfectos) {
//    if (viandasRestantesParaNotificar > 0 && getViandas().size() <= viandasRestantesParaNotificar) {
//      notificarEvento("Quedan pocas viandas en la heladera: " + nombre);
//    }
//    if (viandasFaltantesParaLlenar > 0 && (getCapacidad() - getViandas().size()) <= viandasFaltantesParaLlenar) {
//      notificarEvento("Faltan pocas viandas para llenar la heladera: " + nombre);
//    }
//    if (notificarDesperfectos && heladeraSufrioDesperfecto()) {
//      notificarEvento("La heladera " + nombre + " sufrió un desperfecto.");
//    }
//  }


//  @Scheduled(cron = "0 */5 * * * *")
//  public void enviarTemperatura() {
//    Random random = new Random();
//    int delta = 5;
//    double minExtremo = this.tempMinAceptable - delta;
//    double maxExtremo = this.tempMaxAceptable + delta;
//    double temperatura = random.nextDouble((maxExtremo - minExtremo) + minExtremo);
//    System.out.println("🌡 Temperatura de la heladera " + this.nombre + ": " + temperatura + "°C");
//    String mensaje = "Heladera " + this.nombre + " - Temperatura: " + temperatura + "°C";
//
//    System.out.println("📡 Enviando mensaje: " + mensaje);
//    amqpTemplate.convertAndSend("temperaturas", mensaje);
//  }


}
