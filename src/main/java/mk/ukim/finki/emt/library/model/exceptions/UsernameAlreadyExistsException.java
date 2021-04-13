package mk.ukim.finki.emt.library.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username){
        super("User with " + username + " already exists");
    }
}
