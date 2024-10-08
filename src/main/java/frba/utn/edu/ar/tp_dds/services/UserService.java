package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.repositories.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final BaseRepository<User, Long> userRepository;

  public UserService(BaseRepository<User, Long> userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public Optional<User> findByUsernameAndPassword(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }
}
