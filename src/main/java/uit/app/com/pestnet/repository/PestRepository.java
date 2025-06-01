package uit.app.com.pestnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.app.com.pestnet.model.Pest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PestRepository extends JpaRepository<Pest, UUID> {
    Optional<Pest> findByName(String name);
    List<Pest> findAllByIsDeletedFalse();
}
