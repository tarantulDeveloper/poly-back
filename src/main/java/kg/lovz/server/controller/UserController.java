package kg.lovz.server.controller;

import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.UserUpdateRequest;
import kg.lovz.server.dto.response.UserInfo;
import kg.lovz.server.entity.User;
import kg.lovz.server.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;
    @GetMapping
    public List<UserInfo> getAll() {
        return userService.getAll();
    }

    @GetMapping("/me")
    public UserInfo getMe(@AuthenticationPrincipal User user) {
        return userService.getMe(user);
    }

    @PutMapping("/me")
    public UserInfo updateMe(@AuthenticationPrincipal User user, @ModelAttribute UserUpdateRequest request) {
        return userService.updateMe(user, request);
    }

    @PostMapping
    public UserInfo register(@RequestBody LoginRequest request) {
        return userService.register(request);
    }
}
