package kg.lovz.server.mapper;

import kg.lovz.server.dto.response.UserInfo;
import kg.lovz.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserInfo toUserInfo(User user);
}
