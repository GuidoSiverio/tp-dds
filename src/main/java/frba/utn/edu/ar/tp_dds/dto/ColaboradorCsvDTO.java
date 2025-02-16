package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColaboradorCsvDTO {

    private String tipoDoc;
    private String documento;
    private String nombre;
    private String apellido;
    private String mail;
    private String fechaColab;
    private String formaColab;
    private int cantidad;

    public ColaboradorCsvDTO(String tipoDoc, String documento, String nombre, String apellido, String mail, String fechaColab, String formaColab, int cantidad) {
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fechaColab = fechaColab;
        this.formaColab = formaColab;
        this.cantidad = cantidad;
    }
}
