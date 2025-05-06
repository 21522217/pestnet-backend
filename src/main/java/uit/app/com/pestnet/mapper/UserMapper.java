package uit.app.com.pestnet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uit.app.com.pestnet.dto.UserRequestDTO;
import uit.app.com.pestnet.dto.UserResponseDTO;
import uit.app.com.pestnet.model.User;

/**
 * @author trong-khiem
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert from request DTO to Entity (no password hashing here)
    User toEntity(UserRequestDTO dto);

    // Convert from Entity to Response DTO
    UserResponseDTO toResponseDTO(User user);

    // Optional: Update existing entity with DTO fields (e.g. for PATCH)
    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User user);
}
