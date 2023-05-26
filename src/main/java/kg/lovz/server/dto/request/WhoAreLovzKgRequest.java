package kg.lovz.server.dto.request;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record WhoAreLovzKgRequest(
        String header,
        String text,
        String photoAltText,
        @Nullable MultipartFile photo
) {
}
