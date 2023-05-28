package kg.lovz.server.service;

import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.UserUpdateRequest;
import kg.lovz.server.dto.response.UserInfo;
import kg.lovz.server.entity.User;

import java.util.List;

public interface UserService {
    List<UserInfo> getAll();

    UserInfo getMe(User user);

    UserInfo updateMe(User user, UserUpdateRequest request);

    UserInfo register(LoginRequest request);
}
