package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter
@Setter
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDni;
    private Long nroDni;
    private Long cuil;
    private String medioDeContacto;
    private String areaDeCobertura;

    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.nombre = tecnicoDTO.getNombre();
        this.apellido = tecnicoDTO.getApellido();
        this.tipoDni = tecnicoDTO.getTipoDni();
        this.nroDni = tecnicoDTO.getNroDni();
        this.cuil = tecnicoDTO.getCuil();
        this.medioDeContacto = tecnicoDTO.getMedioDeContacto();
        this.areaDeCobertura = tecnicoDTO.getAreaDeCobertura();
    }

    public void update(TecnicoDTO tecnicoDTO) {
        Optional.ofNullable(tecnicoDTO.getNombre()).ifPresent(this::setNombre);
        Optional.ofNullable(tecnicoDTO.getApellido()).ifPresent(this::setApellido);
        Optional.ofNullable(tecnicoDTO.getTipoDni()).ifPresent(this::setTipoDni);
        Optional.ofNullable(tecnicoDTO.getNroDni()).ifPresent(this::setNroDni);
        Optional.ofNullable(tecnicoDTO.getCuil()).ifPresent(this::setCuil);
        Optional.ofNullable(tecnicoDTO.getMedioDeContacto()).ifPresent(this::setMedioDeContacto);
        Optional.ofNullable(tecnicoDTO.getAreaDeCobertura()).ifPresent(this::setAreaDeCobertura);
    }

    public Tecnico() {
    }
}
