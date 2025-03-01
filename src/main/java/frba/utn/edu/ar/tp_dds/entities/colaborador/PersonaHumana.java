package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorCsvDTO;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.incidente.FallaTecnica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.DiscriminatorValue;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PersonaHumana")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaHumana extends Colaborador{

  protected String nombre;
  protected String apellido;
  protected LocalDateTime fechaNacimiento;
  protected String tipoDoc;
  protected String nroDoc;

  public PersonaHumana(ColaboradorDTO colaboradorDTO) {
    super(colaboradorDTO.getDireccion(), colaboradorDTO.getMedioDeContacto(), colaboradorDTO.getNumero(), colaboradorDTO.getEmail());
    this.nombre = colaboradorDTO.getNombre();
    this.apellido = colaboradorDTO.getApellido();
    this.fechaNacimiento = LocalDateTime.parse(colaboradorDTO.getFechaDeNacimiento());
  }

  public PersonaHumana(ColaboradorCsvDTO colab) {
    super("", "Email", "", colab.getMail());
    this.tipoDoc = colab.getTipoDoc();
    this.nroDoc = colab.getDocumento();
    this.nombre = colab.getNombre();
    this.apellido = colab.getApellido();
  }

  public PersonaHumana() {
  }
}
