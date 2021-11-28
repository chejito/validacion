package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> register(UserDto userDto);
    ResponseEntity<?> addPhotosAndPhone(Long id, OnboardingRequestDto onboardingRequestDto);
    ResponseEntity<?> validate(Long id);
    ResponseEntity<?> getUser(Long id);
    ResponseEntity<?> getAllUsers();
}
