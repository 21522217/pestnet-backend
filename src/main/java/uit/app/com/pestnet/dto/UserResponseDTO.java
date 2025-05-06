package uit.app.com.pestnet.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author trong-khiem
 */
@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
