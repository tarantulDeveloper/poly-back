package kg.lovz.server.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshAccessTokenRequest(
        @NotBlank(message = "refresh token cannot be empty!")
        String refreshToken
) {
}
