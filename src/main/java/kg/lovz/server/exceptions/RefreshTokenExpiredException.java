package kg.lovz.server.exceptions;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException() {
        super("Jwt Expired!");
    }
}
