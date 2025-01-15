package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.entities.contribucion.Contribucion;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import jakarta.persistence.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "colaborador")
  @JsonIgnore
  private List<Contribucion> contribuciones = new ArrayList<>();

  @OneToMany(mappedBy = "colaborador")
  private List<Heladera> heladerasRegistradas;

  @OneToMany(mappedBy = "colaborador")
  private List<Tarjeta> tarjetasRepartidas;

  @OneToOne
  private Tarjeta tarjeta;

  @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Incidente> fallasTecnicas;

  public Colaborador() {
  }

  public Colaborador(String direccion, String medioDeContacto) {
    this.direccion = direccion;
    this.medioDeContacto = medioDeContacto;
  }

  public void add(Contribucion contribucion) {
    this.contribuciones.add(contribucion);
    contribucion.setColaborador(this);
  }

  public void add(Incidente fallaTecnica){
    this.fallasTecnicas.add(fallaTecnica);
    fallaTecnica.setColaborador(this);
  }

  @Override
  public void notificar(String mensaje) {
    System.out.println("Notificación para colaborador " + getId() + ": " + mensaje);
  }

  public void aceptarSugerencia(List<Heladera> heladerasSugeridas) {
    // Lógica para aceptar o rechazar sugerencias
    System.out.println("Sugerencias aceptadas: " + heladerasSugeridas);
  }

  @Override
  public void notificar(Incidente incidente) {

  }
}
