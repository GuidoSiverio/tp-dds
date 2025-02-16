package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
import frba.utn.edu.ar.tp_dds.repositories.UserRepository;
import java.util.Optional;

import frba.utn.edu.ar.tp_dds.validator.passwords.ValidadorContraseniasUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ValidadorContraseniasUsuario validadorContraseniasUsuario;
  private final ColaboradorRepository colaboradorRepository;
  private final TecnicoRepository tecnicoRepository;

  @Autowired
  public UserService(UserRepository userRepository, ValidadorContraseniasUsuario validadorContraseniasUsuario, ColaboradorRepository colaboradorRepository, TecnicoRepository tecnicoRepository) {
    this.userRepository = userRepository;
    this.validadorContraseniasUsuario = validadorContraseniasUsuario;
    this.colaboradorRepository = colaboradorRepository;
    this.tecnicoRepository = tecnicoRepository;
  }

  public void saveUser(User user) {
    if (user.getRol() != null) {
      user.setRol("COLABORADOR");
    }
    save(user);
  }

  public Optional<User> findByUsernameAndPassword(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }

  public void validarContrasenia(String password) {
    validadorContraseniasUsuario.validarContrasenia(password);
  }

  public Colaborador checkColaborador(User user) {
    User foundUser = userRepository.findByUsername(user.getUsername()).orElse(null);
    return colaboradorRepository.findByUserId(foundUser.getId()).orElse(null);
  }

  public void saveColab(Colaborador colaborador, String username, String password) {
    findByUsernameAndPassword(username, password).ifPresent(user -> {
      colaborador.setUser(user);
      saveUser(user);
    });
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public Tecnico checkTecnico(User user) {
    return tecnicoRepository.findAll().stream().filter(tecnico -> tecnico.getUser().equals(user)).findFirst().orElse(null);
  }

  public Boolean checkIfExistUser(User user) {
    return userRepository.findByUsername(user.getUsername()).isPresent();
  }
}
