package kg.lovz.server.service;

import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.RefreshAccessTokenRequest;
import kg.lovz.server.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

}
