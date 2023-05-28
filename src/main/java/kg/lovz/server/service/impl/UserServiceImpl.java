package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.LoginRequest;
import kg.lovz.server.dto.request.UserUpdateRequest;
import kg.lovz.server.dto.response.UserInfo;
import kg.lovz.server.entity.User;
import kg.lovz.server.entity.enums.Roles;
import kg.lovz.server.exceptions.BadRequestException;
import kg.lovz.server.mapper.UserMapper;
import kg.lovz.server.repo.UserRepository;
import kg.lovz.server.service.CloudinaryService;
import kg.lovz.server.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final CloudinaryService cloudinaryService;
    final PasswordEncoder passwordEncoder;
    @Override
    public List<UserInfo> getAll() {
        return userRepository.findAll().stream().map(userMapper::toUserInfo).toList();
    }

    @Override
    public UserInfo getMe(User user) {
        return userMapper.toUserInfo(user);
    }

    @Override
    public UserInfo updateMe(User user, UserUpdateRequest request) {
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        if(request.photo() != null) {
            user.setPhotoUrl(cloudinaryService.upload(request.photo()));
        }

        return userMapper.toUserInfo(userRepository.save(user));

    }

    @Override
    public UserInfo register(LoginRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("User exist!");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setPhotoUrl("https://cdn.icon-icons.com/icons2/2468/PNG/512/user_icon_149329.png");
        user.setRoles(Set.of(Roles.ADMIN));
        return userMapper.toUserInfo(userRepository.save(user));
    }
}
