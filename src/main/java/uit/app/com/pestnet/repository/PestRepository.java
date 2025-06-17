package uit.app.com.pestnet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uit.app.com.pestnet.model.Pest;

import java.util.Optional;
import java.util.UUID;

public interface PestRepository extends JpaRepository<Pest, UUID> {
    Optional<Pest> findByName(String name);
    Page<Pest> findAllByDeletedFalse(Pageable pageable);
    Optional<Pest> findByScientificNameIgnoreCaseAndDeletedFalse(String scientificName);
    Optional<Pest> findByScientificName(String scientificName);
    Optional<Pest> findByScientificNameIgnoreCase(String scientificName);
    Optional<Pest> findByNameIgnoreCase(String name);
}
