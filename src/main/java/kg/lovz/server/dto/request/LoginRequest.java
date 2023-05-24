package kg.lovz.server.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
