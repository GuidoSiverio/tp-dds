package frba.utn.edu.ar.tp_dds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColaboradorDTO {

    private String direccion;
    private String medioDeContacto;
    private String nombre;
    private String apellido;
    private String fechaDeNacimiento;
    private String razonSocial;
    private String tipo;
    private String rubro;
    private String username;
    private String password;

    public ColaboradorDTO(List<String> fields) {
        this.direccion = fields.get(0);
        this.medioDeContacto = fields.get(1);
        this.nombre = fields.get(2);
        this.apellido = fields.get(3);
        this.fechaDeNacimiento = fields.get(4);
        this.razonSocial = fields.get(5);
        this.rubro = fields.get(6);
        this.tipo = fields.get(7);
    }

    public ColaboradorDTO() {
    }
}
