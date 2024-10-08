package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameAndPassword(String username, String password);
}
