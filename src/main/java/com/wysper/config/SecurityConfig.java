package com.wysper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] WHITELIST_URLS = {
            // Public API endpoints
            "/api/public/**",

            // Swagger UI and OpenAPI documentation
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };
    private static final String[] AUTH_URLS = {
            "/api/auth/**",
    };

    private static final String[] ADMIN_URLS = {
            "/api/v1/users/**",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(WHITELIST_URLS).permitAll()
                        .requestMatchers(HttpMethod.POST ,AUTH_URLS).permitAll()
                        .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
                        .anyRequest().denyAll()

                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }


}
