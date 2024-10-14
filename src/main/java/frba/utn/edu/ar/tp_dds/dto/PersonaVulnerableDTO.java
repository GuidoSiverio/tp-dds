package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaVulnerableDTO {
    private String nombre;
    private String fechaNacimiento;
    private String fechaRegistro;
    private String tipoDocumento;
    private Long dni;
    private String direccion;
    private Boolean poseeMenores;
    private Integer cantMenores;
}
