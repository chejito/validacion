package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final UserServiceImpl userService;

    public OnboardingController(UserServiceImpl userService){
        this.userService = userService;
    }

    /*@PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/dni", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto){

        return userService.addPhotosAndPhone(onboardingRequestDto);
    }*/

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/dni/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addPhotosAndPhone(@PathVariable Long id, OnboardingRequestDto onboardingRequestDto){

        return userService.addPhotosAndPhone(id, onboardingRequestDto);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?>  getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}
