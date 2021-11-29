package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.UserDto;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz del servicio de usuario
 */
public interface UserService {

    ResponseEntity<?> register(UserDto userDto);
    ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto);
    ResponseEntity<?> validate(String username);
    ResponseEntity<?> getUserByUsername(String username);
    ResponseEntity<?> getAllUsers();
}
