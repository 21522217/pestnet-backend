package uit.app.com.pestnet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pest {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Name is required")
    private String name;

    @ElementCollection
    @CollectionTable(name = "pest_regions", joinColumns = @JoinColumn(name = "pest_id"))
    @Column(name = "region")
    private List<String> regions;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "biological_characteristics", columnDefinition = "TEXT")
    private String biologicalCharacteristics;

    @Column(name = "control_methods", columnDefinition = "TEXT")
    private String controlMethods;

    @Column(name = "harm_level")
    private String harmLevel;

    @Column(name = "occurrence_count")
    private Integer occurrenceCount;

    @Column(name = "pest_url", columnDefinition = "TEXT", nullable = false)
    private String pestUrl = "https://www.inaturalist.org/taxa/52045-Cnaphalocrocis-medinalis";

    @ElementCollection
    @CollectionTable(name = "pest_insecticides", joinColumns = @JoinColumn(name = "pest_id"))
    @Column(name = "insecticide")
    private List<String> pestInsecticide;

    @ElementCollection
    @CollectionTable(name = "related_images")
    private List<String> relatedImages = List.of();

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;
}
