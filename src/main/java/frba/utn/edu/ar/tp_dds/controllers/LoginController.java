package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginController {

  private final UserService userService;

  private List<User> users = new ArrayList<>();

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    userService.save(user);
    return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
  }

  @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> loginUser(@RequestBody User user) {
    Optional<User> foundUser = users.stream()
        .filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
        .findFirst();
    if (foundUser.isPresent()) {
      return new ResponseEntity<>("Login successful!", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
  }


  @GetMapping(path = "/users", produces = "application/json")
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(users, org.springframework.http.HttpStatus.OK);
  }

}