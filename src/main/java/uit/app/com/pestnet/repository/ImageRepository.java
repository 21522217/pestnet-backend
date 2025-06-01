package uit.app.com.pestnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.app.com.pestnet.model.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
