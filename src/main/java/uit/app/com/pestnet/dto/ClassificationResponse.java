package uit.app.com.pestnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uit.app.com.pestnet.model.Classification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationResponse {
    private UUID classificationId;
    private UUID pestId;
    private String pestName;
    private String modelName;
    private float confidence;
    private LocalDateTime classifiedAt;
    private String imageUrl;
    private List<String> pestRegions;
    private String pestScientificName;
    private String pestDescription;
    private String pestUrl;
    private List<String> pestInsecticide;

    public static ClassificationResponse fromEntity(Classification classification) {
        return ClassificationResponse.builder()
                .classificationId(classification.getId())
                .imageUrl(classification.getImage().getImageUrl())
                .pestId(classification.getPest().getId())
                .pestName(classification.getPest().getName())
                .pestScientificName(classification.getPest().getScientificName())
                .pestDescription(classification.getPest().getDescription())
                .pestUrl(classification.getPest().getPestUrl())
                .pestRegions(classification.getPest().getRegions())
                .pestInsecticide(classification.getPest().getPestInsecticide())
                .modelName(classification.getModel().getModelName())
                .confidence(classification.getConfidence())
                .classifiedAt(classification.getClassifiedAt())
                .build();
    }
}