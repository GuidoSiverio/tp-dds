package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.UserRepository;
import java.util.Optional;

import frba.utn.edu.ar.tp_dds.validator.passwords.ValidadorContraseniasUsuario;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ValidadorContraseniasUsuario validadorContraseniasUsuario;

  public UserService(UserRepository userRepository, ValidadorContraseniasUsuario validadorContraseniasUsuario) {
    this.userRepository = userRepository;
    this.validadorContraseniasUsuario = validadorContraseniasUsuario;
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public Optional<User> findByUsernameAndPassword(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }

  public void validarContrasenia(String password) {
    validadorContraseniasUsuario.validarContrasenia(password);
  }

  public Colaborador checkColaborador(User user) {
    return findByUsernameAndPassword(user.getUsername(), user.getPassword()).get().getColaborador();
  }

  public void saveColab(Colaborador colaborador, String username, String password) {
    findByUsernameAndPassword(username, password).ifPresent(user -> {
      user.setColaborador(colaborador);
      save(user);
    });
  }
}
