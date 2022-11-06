package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
