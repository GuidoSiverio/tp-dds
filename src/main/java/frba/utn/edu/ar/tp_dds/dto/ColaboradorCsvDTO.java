package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColaboradorCsvDTO {

    private String tipoDoc;
    private String nroDoc;
    private String nombre;
    private String apellido;
    private String mail;
    private String fechaColab;
    private String formaColab;
    private String cantidad;


    public ColaboradorCsvDTO(List<String> fields) {
        this.tipoDoc = fields.get(0);
        this.nroDoc = fields.get(1);
        this.nombre = fields.get(2);
        this.apellido = fields.get(3);
        this.mail = fields.get(4);
        this.fechaColab = fields.get(5);
        this.formaColab = fields.get(6);
        this.cantidad = fields.get(7);
    }
}
