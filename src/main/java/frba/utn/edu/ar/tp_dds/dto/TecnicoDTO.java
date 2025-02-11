package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO {

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private Long documento;
    private Long cuil;
    private String medioContacto;
    private String numero;
    private String email;
    private String areaCobertura;
    private String user;
    private String password;

}
