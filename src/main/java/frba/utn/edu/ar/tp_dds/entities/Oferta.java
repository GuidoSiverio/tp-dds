package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double puntosNecesarios;
    private String imagen;

    public Oferta(OfertaDTO ofertaDTO) {
        this.nombre = ofertaDTO.getNombre();
        this.puntosNecesarios = ofertaDTO.getPuntosNecesarios();
        this.imagen = ofertaDTO.getImagen();
    }

    public Oferta() {
    }
}
