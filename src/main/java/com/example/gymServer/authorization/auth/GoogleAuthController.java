package com.example.gymServer.authorization.auth;

import com.example.gymServer.authorization.config.JwtService;
import com.example.gymServer.authorization.token.Token;
import com.example.gymServer.authorization.token.TokenRepository;
import com.example.gymServer.authorization.token.TokenType;
import com.example.gymServer.authorization.user.Role;
import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${google.clientId}")
    private String googleClientId;

    @PostMapping("/google-authenticate")
    public AuthenticationResponse authenticateWithGoogle(@RequestBody GoogleAuthRequest request) throws Exception {


        if (request.getIdToken() == null || request.getIdToken().isEmpty()) {
            throw new Exception("Brak tokena ID lub jest on pusty.");
        }

        System.out.println("Received token: " + request.getIdToken());


        // Weryfikuj token od Google
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(request.getIdToken());

        if (idToken == null) {
            throw new Exception("Nieprawidłowy token Google ID lub weryfikacja tokena nie powiodła się.");
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            // Sprawdź, czy użytkownik istnieje
            Optional<User> existingUser = userRepository.findByEmail(email);
            String tempPassword = generateTemporaryPassword();
            User user;
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                // Zarejestruj nowego użytkownika na podstawie danych z Google
                user = User.builder()
                        .firstname((String) payload.get("given_name"))
                        .lastname((String) payload.get("family_name"))
                        .email(email)
                        .password(passwordEncoder.encode(generateTemporaryPassword()))  // Dodaj hasło domyślne
                        .role(Role.USER)
                        .build();
                userRepository.save(user);
            }

            // Wygeneruj token JWT
            String jwtToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            saveUserToken(user, jwtToken);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new Exception("Invalid Google ID token.");
        }
    }

    private String generateTemporaryPassword() {
        // Generowanie bezpiecznego tymczasowego hasła
        return "TempPassword123!"; // Możesz użyć bardziej zaawansowanego generatora haseł
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
