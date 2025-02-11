package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  private String rol;

  @OneToOne
  private Colaborador colaborador;

  @OneToOne
  private Tecnico tecnico;

  public User(String user, String password, String rol, Tecnico tecnico) {
    this.username = user;
    this.password = password;
    this.rol = rol;
    this.tecnico = tecnico;
  }

  public User() {
  }
}


