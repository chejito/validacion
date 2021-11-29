package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del proceso Onboarding, en el que el usuario añade su número de teléfono y dos fotografías
 * de su documento de identidad y se guardarán en un servicio de alojamiento en la nube.
 */
@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final UserServiceImpl userService;

    public OnboardingController(UserServiceImpl userService){
        this.userService = userService;
    }

    /**
     * Método que actualiza un usuario en con el número de teléfono y el enlace a la url de las dos imágenes
     * @param onboardingRequestDto La información para actualizar.
     * @return El Usuario actualizado y un mensaje de texto.
     */
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/dni", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto){

        return userService.addPhotosAndPhone(onboardingRequestDto);
    }

    /**
     * Método que lista un usuario de la base de datos.
     * @param username Nombre de usuario del usuario a listar.
     * @return Usuario solicitado.
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{username}")
    public ResponseEntity<?>  getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
