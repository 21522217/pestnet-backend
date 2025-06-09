package uit.app.com.pestnet.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uit.app.com.pestnet.dto.UserRequestDTO;
import uit.app.com.pestnet.dto.UserResponseDTO;
import uit.app.com.pestnet.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T21:59:24+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return userResponseDTO;
    }

    @Override
    public void updateEntityFromDto(UserRequestDTO dto, User user) {
        if ( dto == null ) {
            return;
        }
    }
}
