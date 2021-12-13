package ob.proyecto.validacion.services.user;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.OnePhotoRequestDto;
import ob.proyecto.validacion.dto.UserDto;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz del servicio de usuario
 */
public interface UserService {

    ResponseEntity<?> register(UserDto userDto);
    ResponseEntity<?> addPhotos(Integer hashcode, OnboardingRequestDto onboardingRequestDto);
    ResponseEntity<?> addOnePhoto(Integer hashcode, OnePhotoRequestDto onePhotoRequestDto);
    ResponseEntity<?> validate(String username);
    ResponseEntity<?> getUserByUsername(String username);
    ResponseEntity<?> getUserByHashcode(Integer hashcode);
    ResponseEntity<?> getAllUsers();
}
