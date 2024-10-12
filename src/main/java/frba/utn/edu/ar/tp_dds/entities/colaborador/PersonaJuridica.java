package frba.utn.edu.ar.tp_dds.entities.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import jakarta.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

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
    super(colaboradorDTO.getDireccion(), colaboradorDTO.getMedioDeContacto());
    this.razonSocial = colaboradorDTO.getRazonSocial();
    this.tipo = colaboradorDTO.getTipo();
    this.rubro = colaboradorDTO.getRubro();
  }

  public PersonaJuridica() {}
}
