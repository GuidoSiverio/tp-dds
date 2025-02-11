package frba.utn.edu.ar.tp_dds.repositories;

import frba.utn.edu.ar.tp_dds.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameAndPassword(String username, String password);

  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(String username);

  @Modifying
  @Query(value = "DELETE FROM User WHERE id IN (SELECT id FROM Tecnico WHERE id = ?1)", nativeQuery = true)
  void deleteByTecnicoId(Long tecnicoId);
}
