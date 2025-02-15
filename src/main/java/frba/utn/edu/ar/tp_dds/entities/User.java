package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

  public User(String user, String password, String rol) {
    this.username = user;
    this.password = password;
    this.rol = rol;
  }

  public User() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true; // Comparación al mismo objeto.
    if (o == null || getClass() != o.getClass()) return false; // Verifica la clase.
    User user = (User) o;
    return username != null && username.equals(user.username) &&
            password != null && password.equals(user.password);
  }


  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

}


