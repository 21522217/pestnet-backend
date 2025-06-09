package uit.app.com.pestnet.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PestDto {
    private UUID id;
    private String name;
    private List<String> regions;
    private String scientificName;
    private String description;
    private String biologicalCharacteristics;
    private String controlMethods;
    private String harmLevel;
    private String pestUrl;
    private List<String> pestInsecticide;
    private List<String> relatedImages;
    private boolean deleted;
}
