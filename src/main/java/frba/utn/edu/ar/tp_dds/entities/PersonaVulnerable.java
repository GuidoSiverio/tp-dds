package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonaVulnerable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private LocalDateTime fechaNacimiento;
  private LocalDateTime fechaRegistro;
  private String tipoDocumento;
  private Long dni;
  private String direccion;
  private boolean poseeMenores;
  private int cantMenores;
  @OneToMany
  @JoinColumn(name = "persona_vulnerable_id")
  private List<Vianda> viandas;
  @OneToOne
  private Tarjeta tarjeta;


  public PersonaVulnerable(PersonaVulnerableDTO personaVulnerableDTO) {
    this.nombre = personaVulnerableDTO.getNombre();
    this.fechaNacimiento = LocalDateTime.parse(personaVulnerableDTO.getFechaNacimiento());
    this.fechaRegistro = LocalDateTime.now();
    this.tipoDocumento = personaVulnerableDTO.isPoseeDni() ? personaVulnerableDTO.getTipoDni() : null;
    this.dni = personaVulnerableDTO.isPoseeDni() ? Long.valueOf(personaVulnerableDTO.getNumeroDni()) : null;
    this.direccion = personaVulnerableDTO.isSituacionDeCalle() ? null : personaVulnerableDTO.getDomicilio();
    this.cantMenores = personaVulnerableDTO.isTieneMenoresACargo() ? personaVulnerableDTO.getCantidadMenoresACargo() : 0;
    this.viandas = new ArrayList<>();
  }

  public PersonaVulnerable() {
  }
}
