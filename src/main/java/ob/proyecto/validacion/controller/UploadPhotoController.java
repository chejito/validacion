package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingPhotoDto;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/upload")
public class UploadPhotoController {

    private final UserServiceImpl userService;

    public UploadPhotoController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/photo", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?>  addPhoto(OnboardingPhotoDto onboardingPhotoDto) {
        return userService.addPhoto(onboardingPhotoDto);
    }
}
