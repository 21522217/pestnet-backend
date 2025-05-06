package uit.app.com.pestnet.config;

/**
 * @author trong-khiem
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//  return http.csrf(customizer -> customizer.disable())
//          .authorizeHttpRequests(request -> request.anyRequest().authenticated())
//          .httpBasic(Customizer.withDefaults())
//          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//          .build();
        return null;
    }
}