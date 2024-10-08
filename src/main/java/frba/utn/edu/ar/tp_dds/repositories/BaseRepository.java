package frba.utn.edu.ar.tp_dds.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
  Optional<T> findByUsernameAndPassword(String username, String password);
}
