package kg.lovz.server.dto.response;

import java.util.List;

public record UserInfo(
        int id,
        String username,
        String firstName,
        String lastName,
        String photoUrl,
        List<String> roles
) {
}
