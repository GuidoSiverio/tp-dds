package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.contribucion.Contribucion;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import jakarta.persistence.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Colaborador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String direccion;
  private String medioDeContacto;

  @OneToMany(mappedBy = "colaborador")
  private List<Contribucion> contribuciones;

  @OneToMany
  @JoinColumn(name = "colaborador_id")
  private List<PersonaVulnerable> personasRegistradas;

  @OneToMany(mappedBy = "colaborador")
  private List<Heladera> heladerasRegistradas;

  @OneToMany(mappedBy = "colaborador")
  private List<Tarjeta> tarjetasRepartidas;

  public Colaborador() {
  }

  public Colaborador(String direccion, String medioDeContacto) {
    this.direccion = direccion;
    this.medioDeContacto = medioDeContacto;
  }

  public void add(Contribucion contribucion) {
    this.contribuciones.add(contribucion);
  }

    public void add(PersonaVulnerable personaVulnerable) {
        this.personasRegistradas.add(personaVulnerable);
    }
}
