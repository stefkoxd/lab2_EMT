package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String name, String surname, Role role, String username, String password, String repeatPassword);
}
