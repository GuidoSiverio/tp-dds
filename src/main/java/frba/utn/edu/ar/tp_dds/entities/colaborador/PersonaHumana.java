package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorCsvDTO;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
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
  protected String mail;

  public PersonaHumana(ColaboradorDTO colaboradorDTO) {
    super(colaboradorDTO.getDireccion(), colaboradorDTO.getMedioDeContacto());
    this.nombre = colaboradorDTO.getNombre();
    this.apellido = colaboradorDTO.getApellido();
    this.fechaNacimiento = LocalDateTime.parse(colaboradorDTO.getFechaDeNacimiento());
  }

  public PersonaHumana(ColaboradorCsvDTO colab) {
    this.tipoDoc = colab.getTipoDoc();
    this.nroDoc = colab.getNroDoc();
    this.nombre = colab.getNombre();
    this.apellido = colab.getApellido();
    this.mail = colab.getMail();
  }

    public PersonaHumana() {
    }
}
