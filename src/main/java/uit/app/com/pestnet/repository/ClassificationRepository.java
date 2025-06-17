package uit.app.com.pestnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.app.com.pestnet.model.Classification;


import java.util.List;
import java.util.UUID;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, UUID> {
    List<Classification> findByImage_User_IdAndImage_IsDeletedFalseOrderByClassifiedAtDesc(UUID userId);
    List<Classification> findByImage_User_IdAndImage_IsDeletedFalseOrderByConfidenceDesc(UUID userId);
}
