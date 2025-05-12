package uit.app.com.pestnet.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.config.CustomUserDetails;
import uit.app.com.pestnet.dto.*;
import uit.app.com.pestnet.exception.HttpResponseException;
import uit.app.com.pestnet.mapper.UserMapper;
import uit.app.com.pestnet.model.User;
import uit.app.com.pestnet.repository.UserRepository;
import uit.app.com.pestnet.service.JwtService;
import uit.app.com.pestnet.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author trong-khiem
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole("user");
        user.setDeleted(false);
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public AuthenticationResponse signup(SignupDTO signupDTO) {
        // Check if username already exists
        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            throw new HttpResponseException.ResourceAlreadyExistsException("Username is already taken");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            throw new HttpResponseException.ResourceAlreadyExistsException("Email is already registered");
        }

        // Create user entity
        User user = User.builder()
                .username(signupDTO.getUsername())
                .email(signupDTO.getEmail())
                .passwordHash(passwordEncoder.encode(signupDTO.getPassword()))
                .role("user")
                .isDeleted(false)
                .build();

        User savedUser = userRepository.save(user);

        // Generate JWT token
        String token = jwtService.generateToken(new CustomUserDetails(savedUser));

        return AuthenticationResponse.builder()
                .token(token)
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginDTO loginDTO) {
        try {
            Optional<User> userOptional;
            if (loginDTO.getUsernameOrEmail().contains("@")) {
                userOptional = userRepository.findByEmail(loginDTO.getUsernameOrEmail());
            } else {
                userOptional = userRepository.findByUsername(loginDTO.getUsernameOrEmail());
            }

            if (userOptional.isEmpty()) {
                throw new HttpResponseException.ResourceAlreadyExistsException("User not found with provided credentials");
            }

            User user = userOptional.get();

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtService.generateToken(new CustomUserDetails(user));
            String refreshToken = jwtService.generateRefreshToken(new CustomUserDetails((user)));

            return AuthenticationResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .username(user.getUsername())
                    .role(user.getRole())
                    .build();

        } catch (AuthenticationException e) {
            throw new HttpResponseException.ResourceNotFoundException("Invalid username/email or password");
        }
    }
}