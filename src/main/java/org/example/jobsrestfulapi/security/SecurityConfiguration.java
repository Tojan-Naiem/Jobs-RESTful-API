package org.example.jobsrestfulapi.security;

import org.example.jobsrestfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
     private UserService userService;
     public SecurityConfiguration(UserService userService){
         this.userService=userService;
     }
     @Bean
    public static PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }

     @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
     ) throws Exception{
         return configuration.getAuthenticationManager();
     }

     @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf->csrf.disable()).authorizeHttpRequests(
                 (auth)->auth.requestMatchers("/api/v1/auth/**").permitAll()
                         .requestMatchers(HttpMethod.GET,"/api/v1/**").permitAll()
                         .anyRequest().permitAll()
         );
         return http.build();
    }

}
