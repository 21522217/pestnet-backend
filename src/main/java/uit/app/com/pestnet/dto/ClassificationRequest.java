package uit.app.com.pestnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationRequest {
    private String imageUrl;
    private String pestName;
    private float confidence;
    private String modelName;
}