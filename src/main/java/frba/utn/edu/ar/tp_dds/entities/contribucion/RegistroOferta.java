package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.Oferta;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "RegistroOferta")
public class RegistroOferta extends Contribucion {

  @OneToOne
  private Oferta oferta;

}
