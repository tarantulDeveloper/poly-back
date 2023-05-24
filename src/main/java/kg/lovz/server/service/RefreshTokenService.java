package kg.lovz.server.service;

import kg.lovz.server.dto.request.RefreshAccessTokenRequest;
import kg.lovz.server.dto.response.LoginResponse;
import kg.lovz.server.entity.User;

public interface RefreshTokenService {
    String generateRefreshToken(User user);

    LoginResponse generateAccessTokenByRefreshToken(RefreshAccessTokenRequest request);
}
