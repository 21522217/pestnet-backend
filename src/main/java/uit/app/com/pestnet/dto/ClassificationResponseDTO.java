package uit.app.com.pestnet.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationResponseDTO {
    private UUID id;
    private String imageUrl;
    private float confidence;
    @Column(name = "classified_at", nullable = false)
    private LocalDateTime classifiedAt;
    private String modelName;
    private PestDto pest;
}
