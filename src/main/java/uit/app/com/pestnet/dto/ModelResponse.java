package uit.app.com.pestnet.dto;

import lombok.Builder;
import lombok.Data;
import uit.app.com.pestnet.model.Model;

import java.util.UUID;

@Data
@Builder
public class ModelResponse {
    private UUID id;
    private String modelSrc;
    private String modelName;

    public static ModelResponse fromEntity(Model model) {
        return ModelResponse.builder()
                .id(model.getId())
                .modelSrc(model.getModelSrc())
                .modelName(model.getModelName())
                .build();
    }
}
