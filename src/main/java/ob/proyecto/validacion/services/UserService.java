package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.security.payload.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

//    ResponseEntity<List<User>> findAll();
//    ResponseEntity<User> findByUsername(String username);
//    boolean fullnameIsUsed(String fullname);
    ResponseEntity<MessageResponse> register(UserDto userDto);
}
