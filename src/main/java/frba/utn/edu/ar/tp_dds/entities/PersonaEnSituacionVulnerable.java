package frba.utn.edu.ar.tp_dds.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonaEnSituacionVulnerable {

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
  @JoinColumn(name = "persona_en_situacion_vulnerable_id")
  private List<Vianda> viandas;

}
