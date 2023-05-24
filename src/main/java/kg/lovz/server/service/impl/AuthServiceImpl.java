package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.RefreshAccessTokenRequest;
import kg.lovz.server.dto.response.LoginResponse;
import kg.lovz.server.dto.response.UserInfo;
import kg.lovz.server.entity.User;
import kg.lovz.server.exceptions.UserNotFoundException;
import kg.lovz.server.jwt.JwtUtils;
import kg.lovz.server.mapper.UserMapper;
import kg.lovz.server.repo.UserRepository;
import kg.lovz.server.service.AuthService;
import kg.lovz.server.service.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    final AuthenticationManager authenticationManager;
    final JwtUtils jwtUtils;
    final RefreshTokenService refreshTokenService;
    final UserMapper userMapper;
    final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.username(), request.password()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        String accessToken = jwtUtils.generate(request.username());
        String refreshToken = refreshTokenService.generateRefreshToken((User) authentication.getPrincipal());
        UserInfo userInfo = userMapper.toUserInfo(userRepository.findByUsername(request.username()).orElseThrow(
                UserNotFoundException::new
        ));

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userInfo(userInfo)
                .build();
    }


}
