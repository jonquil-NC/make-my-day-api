package com.northcoders.makemydayapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/makemyday/sign-in", "/api/v1/makemyday/sign-up").permitAll())
                .authorizeHttpRequests(authHttp ->  authHttp.requestMatchers("/api/v1/makemyday/events","/api/v1/makemyday/restaurants",
                        "/api/v1/skiddle/**",
                        "/api/v1/ticketmaster/**",
                        "/api/v1/geoapify/**",
                        "/api/v1/places/**"
                    ).permitAll())
        ;
        return http.build();
    }

}
