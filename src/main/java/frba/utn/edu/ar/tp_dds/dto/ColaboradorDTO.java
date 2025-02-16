package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorDTO {

    private String direccion;
    private String medioDeContacto;
    private String numero;
    private String email;
    private String nombre;
    private String apellido;
    private String fechaDeNacimiento;
    private String razonSocial;
    private String tipo;
    private String rubro;
    private String username;
    private String password;

    public ColaboradorDTO() {
    }
}
