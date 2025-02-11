package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.responses.LoginResponse;
import frba.utn.edu.ar.tp_dds.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    try {
      userService.validarContrasenia(user.getPassword()); //L4M3j0rC0ntra4s3n4!
      userService.saveColaborador(user);
      return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Password validation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
  public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) {
    Optional<User> foundUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    LoginResponse response = new LoginResponse();
    if (foundUser.isPresent()) {
      response.setSuccess(true);
      response.setMessage("Login successful!");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
      response.setSuccess(false);
      response.setMessage("Invalid credentials");
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping(path = "/check-colaborador", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Colaborador> checkColaborador(@RequestBody User user) {
      Colaborador colaborador = userService.checkColaborador(user);
      if (colaborador != null) {
          return new ResponseEntity<>(colaborador, HttpStatus.OK);
      }
      return new ResponseEntity<>(null, HttpStatus.CONTINUE);
  }

  @PostMapping(path = "/check-tecnico", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Tecnico> checkTecnico(@RequestBody User user) {
      Tecnico tecnico = userService.checkTecnico(user);
      if (tecnico != null) {
          return new ResponseEntity<>(tecnico, HttpStatus.OK);
      }
      return new ResponseEntity<>(null, HttpStatus.CONTINUE);
  }

  @GetMapping(path = "/users", produces = "application/json")
  public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
      Optional<User> user = userService.findByUsername(username);
      return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

}