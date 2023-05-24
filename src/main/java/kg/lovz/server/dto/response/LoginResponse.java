package kg.lovz.server.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken,
        UserInfo userInfo
) {
}
