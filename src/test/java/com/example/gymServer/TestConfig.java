package com.example.gymServer;

import com.example.gymServer.authorization.config.JwtService;
import com.example.gymServer.authorization.config.JwtAuthenticationFilter;
import com.example.gymServer.authorization.token.TokenRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class TestConfig {

    @Bean
    public JwtService jwtService() {
        return Mockito.mock(JwtService.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return Mockito.mock(UserDetailsService.class); // Zamockuj UserDetailsService
    }

    @Bean
    public TokenRepository tokenRepository() {
        return Mockito.mock(TokenRepository.class); // Zamockuj TokenRepository
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            TokenRepository tokenRepository) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService, tokenRepository);
    }
}
