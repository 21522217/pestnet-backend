package uit.app.com.pestnet.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
    /**
     * Loads a user by username
     * @param username the username to search for
     * @return a UserDetails object containing the user's security information
     * @throws UsernameNotFoundException if the user is not found
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}