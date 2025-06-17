package uit.app.com.pestnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationCreateRequest {
    private String imageUrl;
    private String originalName;
    private UUID pestId;
    private float confidence;
    private UUID modelId;
}
