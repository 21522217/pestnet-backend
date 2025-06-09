package uit.app.com.pestnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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
    private Instant classifiedAt;
    private String imageUrl;
    private List<String> pestRegions;
    private String pestScientificName;
    private String pestDescription;
    private String pestUrl;
    private List<String> pestInsecticide;
}
