package kg.lovz.server.dto.request;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record NewsRequest(
        String header,
        String text,
        String photoAltText,
        String mainText,
        @Nullable MultipartFile photo
) {
}
