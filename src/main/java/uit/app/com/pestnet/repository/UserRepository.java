package uit.app.com.pestnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.app.com.pestnet.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * @author trong-khiem
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameAndIsDeletedFalse(String username);

    Optional<User> findByEmailAndIsDeletedFalse(String email);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
