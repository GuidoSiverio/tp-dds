package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Optional;

@Entity
@Getter
@Setter
public class Tecnico implements Suscriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private Long documento;
    private Long cuil;
    private String medioContacto;
    private String areaCobertura;

    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.nombre = tecnicoDTO.getNombre();
        this.apellido = tecnicoDTO.getApellido();
        this.tipoDocumento = tecnicoDTO.getTipoDocumento();
        this.documento = tecnicoDTO.getDocumento();
        this.cuil = tecnicoDTO.getCuil();
        this.medioContacto = tecnicoDTO.getMedioContacto();
        this.areaCobertura = tecnicoDTO.getAreaCobertura();
    }

    public void update(TecnicoDTO tecnicoDTO) {
        Optional.ofNullable(tecnicoDTO.getNombre()).ifPresent(this::setNombre);
        Optional.ofNullable(tecnicoDTO.getApellido()).ifPresent(this::setApellido);
        Optional.ofNullable(tecnicoDTO.getTipoDocumento()).ifPresent(this::setTipoDocumento);
        Optional.ofNullable(tecnicoDTO.getDocumento()).ifPresent(this::setDocumento);
        Optional.ofNullable(tecnicoDTO.getCuil()).ifPresent(this::setCuil);
        Optional.ofNullable(tecnicoDTO.getMedioContacto()).ifPresent(this::setMedioContacto);
        Optional.ofNullable(tecnicoDTO.getAreaCobertura()).ifPresent(this::setAreaCobertura);
    }

    public Tecnico() {
    }

    @RabbitListener(queues = "alertas")
    public void recibirAlerta(String mensaje) {
        System.out.println("Técnico notificado: " + mensaje);
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("Notificación técnica: " + mensaje);
    }

    @Override
    public void notificar(Incidente incidente) {
        System.out.println("Técnico notificado sobre el incidente: " + incidente.getId());
    }

}
