package frba.utn.edu.ar.tp_dds.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {


  @GetMapping("/user")
  public void userController() {
    System.out.println("UserController created");
  }
}
