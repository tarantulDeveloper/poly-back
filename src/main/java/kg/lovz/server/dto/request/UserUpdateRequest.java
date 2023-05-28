package kg.lovz.server.dto.request;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record UserUpdateRequest(
        String firstName,
        String lastName,
        @Nullable MultipartFile photo
) {
}
