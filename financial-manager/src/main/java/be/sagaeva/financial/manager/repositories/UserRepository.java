package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByemail(String email);

}
