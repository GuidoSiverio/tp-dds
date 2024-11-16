package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.contribucion.Contribucion;
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

  @OneToMany
  @JoinColumn(name = "colaborador_id")
  private List<Contribucion> contribuciones;

  @OneToMany
  @JoinColumn(name = "colaborador_id")
  private List<PersonaVulnerable> personasRegistradas;


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
