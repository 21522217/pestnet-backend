package uit.app.com.pestnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Model {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "model_src")
    private String modelSrc;

    @Column(name = "model_name")
    private String modelName;
}
