package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.responses.LoginResponse;
import frba.utn.edu.ar.tp_dds.services.UserService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    userService.save(user);
    return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
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

}