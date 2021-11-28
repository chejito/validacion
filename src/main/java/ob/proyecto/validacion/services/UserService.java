package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.OnboardingPhotoRequestDto;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.dto.ValidationDto;
import ob.proyecto.validacion.security.payload.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> register(UserDto userDto);
    ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto);
    ResponseEntity<?> addPhoto(OnboardingPhotoRequestDto onboardingPhotoRequestDto);
    ResponseEntity<?> validate(Long id);
    ResponseEntity<?> getUser(Long id);
    ResponseEntity<?> getAllUsers();
}
