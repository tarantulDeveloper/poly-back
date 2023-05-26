package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.RefreshAccessTokenRequest;
import kg.lovz.server.dto.response.LoginResponse;
import kg.lovz.server.entity.RefreshToken;
import kg.lovz.server.entity.User;
import kg.lovz.server.exceptions.RefreshTokenExpiredException;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.exceptions.UnauthorizedException;
import kg.lovz.server.exceptions.UserNotFoundException;
import kg.lovz.server.jwt.JwtUtils;
import kg.lovz.server.repo.RefreshTokenRepository;
import kg.lovz.server.service.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    final RefreshTokenRepository refreshTokenRepository;
    final JwtUtils jwtUtils;

    @Value("${jwt_refresh_token_expiration_in_hours}")
    long jwt_refresh_token_expiration_in_hours;

    @Override
    public String generateRefreshToken(User user) {

        RefreshToken refreshToken = null;
        if (refreshTokenRepository.existsByUser(user)) {
            refreshToken = refreshTokenRepository.findByUser(user)
                    .orElseThrow(UserNotFoundException::new);
            refreshTokenRepository.delete(refreshToken);
        }
        refreshToken = generateCompleteNewRefreshToken(user);
        return refreshToken.getRefreshToken();
    }

    @Override
    public LoginResponse generateAccessTokenByRefreshToken(RefreshAccessTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.refreshToken())
                .orElseThrow(() -> new ResourceNotFound("Refresh token not found!"));

        User user = refreshToken.getUser();


        if (isRefreshTokenExpired(refreshToken)) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException();
        }

        refreshTokenRepository.delete(refreshToken);

        return LoginResponse.builder()
                .accessToken(jwtUtils.generate(user.getUsername()))
                .refreshToken(generateRefreshToken(user))
                .build();

    }

    private RefreshToken generateCompleteNewRefreshToken(User user) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(UUID.randomUUID().toString())
                        .user(user)
                        .expirityDate(new Date(System.currentTimeMillis() +
                                1000 * 60 * 60 * jwt_refresh_token_expiration_in_hours))
                        .build()
        );
    }


    private boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpirityDate().before(new Date());
    }
}
