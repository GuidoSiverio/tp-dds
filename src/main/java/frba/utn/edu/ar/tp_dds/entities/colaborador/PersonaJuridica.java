package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import jakarta.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue(value = "PersonaJuridica")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaJuridica extends Colaborador{

  protected String razonSocial;
  protected String tipo;
  protected String rubro;

  public PersonaJuridica(ColaboradorDTO colaboradorDTO) {
    super(colaboradorDTO.getDireccion(), colaboradorDTO.getMedioDeContacto(), colaboradorDTO.getNumero(), colaboradorDTO.getEmail());
    this.razonSocial = colaboradorDTO.getRazonSocial();
    this.tipo = colaboradorDTO.getTipo();
    this.rubro = colaboradorDTO.getRubro();
  }

  public PersonaJuridica() {}


}
