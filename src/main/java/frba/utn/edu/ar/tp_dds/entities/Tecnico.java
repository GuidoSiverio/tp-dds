package frba.utn.edu.ar.tp_dds.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.observer.Suscriptor;
import frba.utn.edu.ar.tp_dds.services.EmailService;
import frba.utn.edu.ar.tp_dds.services.WhatsAppService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
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
    private String numero;
    private String email;
    private String areaCobertura;

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Visita> visitas;

    @OneToOne
    private User user;

    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.nombre = tecnicoDTO.getNombre();
        this.apellido = tecnicoDTO.getApellido();
        this.tipoDocumento = tecnicoDTO.getTipoDocumento();
        this.documento = tecnicoDTO.getDocumento();
        this.cuil = tecnicoDTO.getCuil();
        this.medioContacto = tecnicoDTO.getMedioContacto();
        this.numero = tecnicoDTO.getNumero().isEmpty() ? null : tecnicoDTO.getNumero();
        this.email = tecnicoDTO.getEmail().isEmpty() ? null : tecnicoDTO.getEmail();
        this.areaCobertura = tecnicoDTO.getAreaCobertura();
    }

    public void update(TecnicoDTO tecnicoDTO) {
        Optional.ofNullable(tecnicoDTO.getNombre()).ifPresent(this::setNombre);
        Optional.ofNullable(tecnicoDTO.getApellido()).ifPresent(this::setApellido);
        Optional.ofNullable(tecnicoDTO.getTipoDocumento()).ifPresent(this::setTipoDocumento);
        Optional.ofNullable(tecnicoDTO.getDocumento()).ifPresent(this::setDocumento);
        Optional.ofNullable(tecnicoDTO.getCuil()).ifPresent(this::setCuil);
        Optional.ofNullable(tecnicoDTO.getMedioContacto()).ifPresent(this::setMedioContacto);
        Optional.ofNullable(tecnicoDTO.getNumero()).ifPresent(this::setNumero);
        Optional.ofNullable(tecnicoDTO.getEmail()).ifPresent(this::setEmail);
        Optional.ofNullable(tecnicoDTO.getAreaCobertura()).ifPresent(this::setAreaCobertura);
    }

    public Tecnico() {
    }

    @Override
    public void notificar(EmailService emailService, WhatsAppService whatsAppService, String mensaje) {
        if (medioContacto.equals("Email")) {
            emailService.enviarEmail(getEmail(), "Alerta", mensaje);
        } else if (getMedioContacto().equals("WhatsApp")) {
            whatsAppService.enviarWhatsApp(getNumero(), mensaje);
        } else {
            System.out.println("No se pudo notificar al tecnico " + getId() + " por medio de contacto " + medioContacto);
        }
    }

    @Override
    public void notificar(Incidente incidente) {
        System.out.println("Técnico notificado sobre el incidente: " + incidente.getId());
    }

    public void add(Visita visita) {
        this.visitas.add(visita);
        visita.setTecnico(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tecnico that = (Tecnico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


