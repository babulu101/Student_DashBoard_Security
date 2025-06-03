package com.nit.babul.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nit.babul.service.Student_Ser_Imp;

@Configuration
public class Student_Config {
    @Bean
    public BCryptPasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
            .username("babulu")
            .password(password().encode("babulu"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/student/home").authenticated()
                .anyRequest().permitAll()
            )
                      .formLogin(form -> form
            	      .loginPage("/student/login")        // your custom login page URL (GET)
            	      .loginProcessingUrl("/login")       // Spring Security login processing URL (POST)
            	      
            	      .permitAll()
            	  );
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(Student_Ser_Imp userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(password());
        return authProvider;
    }

    // This bean exposes AuthenticationManager to be @Autowired where needed
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
