package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.RefreshAccessTokenRequest;
import kg.lovz.server.dto.response.LoginResponse;
import kg.lovz.server.service.AuthService;
import kg.lovz.server.service.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    final AuthService authService;
    final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    @SecurityRequirements
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    @SecurityRequirements
    public LoginResponse refreshToken(@RequestBody RefreshAccessTokenRequest request) {
        return refreshTokenService.generateAccessTokenByRefreshToken(request);
    }
}
