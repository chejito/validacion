package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingDto;
import ob.proyecto.validacion.dto.OnboardingPhotoDto;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.dto.ValidationDto;
import ob.proyecto.validacion.security.payload.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<MessageResponse> register(UserDto userDto);
    ResponseEntity<?> addPhotosAndPhone(OnboardingDto onboardingDto);
    ResponseEntity<?> addPhoto(OnboardingPhotoDto onboardingPhotoDto);
    ResponseEntity<MessageResponse> validate(ValidationDto validationDto);
    ResponseEntity<?> getUserDto(ValidationDto validationDto);
}
