package kg.lovz.server.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record HomeContentRequest(
        String header,
        String text,
        String photoAltText,
        MultipartFile photo
) {
}
