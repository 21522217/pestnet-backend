package uit.app.com.pestnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.app.com.pestnet.model.Model;

import java.util.Optional;
import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findByModelName(String modelName);
}
