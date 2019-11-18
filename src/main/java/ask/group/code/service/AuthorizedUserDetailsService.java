package ask.group.code.service;

import ask.group.code.model.User;
import ask.group.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizedUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usrName) throws UsernameNotFoundException {
        Optional<User> usr = userRepository.findByLoginName(usrName);
        usr.orElseThrow(() -> new UsernameNotFoundException("Invalid user name provided"));
        return usr.get();
    }
}
