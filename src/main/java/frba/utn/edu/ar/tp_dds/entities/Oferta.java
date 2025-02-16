package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import jakarta.persistence.*;
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
    private String rubro;
    private Double puntosNecesarios;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    public Oferta(OfertaDTO ofertaDTO) {
        this.nombre = ofertaDTO.getNombre();
        this.rubro = ofertaDTO.getRubro();
        this.puntosNecesarios = ofertaDTO.getPuntosNecesarios();
        this.imagen = ofertaDTO.getImagen();
    }

    public Oferta() {
    }
}
