package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> findAll();
    ResponseEntity<User> findByUsername(String username);
    boolean emailIsUsed(String email);
    ResponseEntity<User> register(UserDto userDto);
}
