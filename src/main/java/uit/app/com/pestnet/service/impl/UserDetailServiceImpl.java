package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.model.User;
import uit.app.com.pestnet.repository.UserRepository;
import uit.app.com.pestnet.config.CustomUserDetails;
import uit.app.com.pestnet.service.UserDetailService;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailAndIsDeletedFalse(email)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
