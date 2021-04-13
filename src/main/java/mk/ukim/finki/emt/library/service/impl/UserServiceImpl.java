package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.enumerations.Role;
import mk.ukim.finki.emt.library.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.emt.library.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.library.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.emt.library.repository.UserRepository;
import mk.ukim.finki.emt.library.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String name, String surname, Role role, String username, String password, String repeatPassword) {

        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();

        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(name,surname,role,username,passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}
