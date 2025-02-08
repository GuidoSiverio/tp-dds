package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.entities.Oferta;
import frba.utn.edu.ar.tp_dds.entities.contribucion.Contribucion;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import frba.utn.edu.ar.tp_dds.services.EmailService;
import frba.utn.edu.ar.tp_dds.services.WhatsAppService;
import jakarta.persistence.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Colaborador implements Suscriptor{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String direccion;
  private String medioDeContacto;
  private String numero;
  private String email;

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Contribucion> contribuciones = new ArrayList<>();

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Heladera> heladerasRegistradas;

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Tarjeta> tarjetasRepartidas;

  @OneToOne
  private Tarjeta tarjeta;

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Incidente> fallasTecnicas;

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Oferta> ofertas;

  public Colaborador() {
  }

  public Colaborador(String direccion, String medioDeContacto, String numero, String email) {
    this.direccion = direccion;
    this.medioDeContacto = medioDeContacto;
    this.numero = numero.isEmpty() ? null : numero;
    this.email = email.isEmpty() ? null : email;
  }

  public void add(Contribucion contribucion) {
    this.contribuciones.add(contribucion);
    contribucion.setColaborador(this);
  }

  public void add(Incidente fallaTecnica){
    this.fallasTecnicas.add(fallaTecnica);
    fallaTecnica.setColaborador(this);
  }

  public void add(Heladera heladera){
    this.heladerasRegistradas.add(heladera);
    heladera.setColaborador(this);
  }


  public void add(Tarjeta tarjeta) {
    this.tarjetasRepartidas.add(tarjeta);
    tarjeta.setColaborador(this);
  }

  public void add(Oferta oferta) {
    this.ofertas.add(oferta);
    oferta.setColaborador(this);
  }

  @RabbitListener(queues = "autorizaciones")
  public void recibirAutorizacion(String mensaje) {
    System.out.println("Colaborador notificado: " + mensaje);
  }

  @Override
  public void notificar(EmailService emailService, WhatsAppService whatsAppService, String mensaje) {
    if (medioDeContacto.equals("Email")) {
      emailService.enviarEmail(getEmail(), direccion, mensaje);
    } else if (medioDeContacto.equals("WhatsApp")) {
      whatsAppService.enviarWhatsApp(getNumero(), mensaje);
    } else {
      System.out.println("No se pudo notificar al colaborador " + getId() + " por medio de contacto " + medioDeContacto);
    }
    System.out.println("Notificación para colaborador " + getId() + ": " + mensaje);
  }

  @Override
  public void notificar(Incidente incidente) {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Colaborador that = (Colaborador) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}



