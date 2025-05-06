package uit.app.com.pestnet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.dto.AuthenticationRequest;
import uit.app.com.pestnet.dto.AuthenticationResponse;
import uit.app.com.pestnet.dto.RegisterRequest;
import uit.app.com.pestnet.model.User;
import uit.app.com.pestnet.repository.UserRepository;
import uit.app.com.pestnet.config.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .isDeleted(false)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetails(user));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsernameAndIsDeletedFalse(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetails(user));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
