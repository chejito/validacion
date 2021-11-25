package ob.proyecto.validacion.security.service;

import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Autentica un usuario en la base de datos
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Username = email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

//        Collection<? extends GrantedAuthority> authorities = user.getRoles();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}
