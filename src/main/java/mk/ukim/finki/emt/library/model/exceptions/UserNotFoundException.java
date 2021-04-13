package mk.ukim.finki.emt.library.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username){
        super("User with username " + username + " not found");
    }
}
