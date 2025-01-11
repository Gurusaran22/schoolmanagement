package com.guru.depend.config;

import com.guru.depend.service.UserService;
import com.guru.depend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/auth/**").permitAll()
                        .requestMatchers("api/students/**").permitAll()
                        .requestMatchers("api/questions/**").hasAnyAuthority(Role.TUTOR.name(),Role.ADMIN.name())
                        .requestMatchers("api/quiz/**").hasAnyAuthority(Role.TUTOR.name(),Role.ADMIN.name())
                        .requestMatchers("api/school/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/studentanswers/**").hasAnyAuthority(Role.ADMIN.name(),Role.TUTOR.name())
                        .requestMatchers("api/students/mark/{id}").hasAnyAuthority(Role.STUDENT.name())
                        .requestMatchers("api/questions/question/{id}").hasAnyAuthority(Role.STUDENT.name())
                        .requestMatchers("api/subject/**").hasAnyAuthority(Role.ADMIN.name(),Role.TUTOR.name())
                        .requestMatchers("api/teachers/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/admin/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/teacher/**").hasAnyAuthority(Role.TUTOR.name(), Role.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

