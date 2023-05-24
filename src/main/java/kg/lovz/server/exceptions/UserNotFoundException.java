package kg.lovz.server.exceptions;

import kg.lovz.server.entity.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found!");
    }
}
