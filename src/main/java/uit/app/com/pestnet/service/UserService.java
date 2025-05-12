package uit.app.com.pestnet.service;

import uit.app.com.pestnet.dto.*;
import uit.app.com.pestnet.model.User;
import java.util.List;
import java.util.UUID;

/**
 * @author trong-khiem
 */
public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    User getUserById(UUID id);
    List<User> getAllUsers();

    AuthenticationResponse signup(SignupDTO signupDTO);
    AuthenticationResponse login(LoginDTO loginDTO);
}