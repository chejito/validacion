package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> register(UserDto userDto);
    ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto);
    ResponseEntity<?> validate(String username);
    ResponseEntity<?> getUser(Long id);
    ResponseEntity<?> getUserByUsername(String username);
    ResponseEntity<?> getAllUsers();
}
