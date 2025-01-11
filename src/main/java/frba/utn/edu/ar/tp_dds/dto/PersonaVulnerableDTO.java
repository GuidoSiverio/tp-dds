package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaVulnerableDTO {

    private String nombre;
    private String fechaNacimiento;
    private boolean situacionDeCalle;
    private String domicilio;
    private boolean poseeDni;
    private String tipoDni;
    private String numeroDni;
    private boolean tieneMenoresACargo;
    private int cantidadMenoresACargo;
    private String tarjeta;
    private Long colaboradorId;
}
