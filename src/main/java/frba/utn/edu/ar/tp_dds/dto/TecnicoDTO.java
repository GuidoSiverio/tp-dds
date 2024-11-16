package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO {

    private String nombre;
    private String apellido;
    private String tipoDni;
    private Long nroDni;
    private Long cuil;
    private String medioDeContacto;
    private String areaDeCobertura;

}
