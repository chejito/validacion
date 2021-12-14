package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.services.hashcode.HashCodeService;
import ob.proyecto.validacion.services.hashcode.HashCodeServiceImpl;
import ob.proyecto.validacion.services.user.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del proceso Onboarding, en el que el usuario añade dos fotografías de su documento de identidad
 * y se guardarán en un servicio de alojamiento en la nube, para su posterior validación.
 */
@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final UserServiceImpl userService;

    public OnboardingController(UserServiceImpl userService){
        this.userService = userService;
    }

    /**
     * Método que actualiza un usuario en con los enlaces a las urls de las dos imágenes
     * @param onboardingRequestDto La información para actualizar.
     * @return El Usuario actualizado y un mensaje de texto.
     */
//    @RequestMapping(value = "/photos/{hashcode}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @PutMapping("/photos/{hashcode}")
    public ResponseEntity<?> addPhotos(@PathVariable String hashcode, OnboardingRequestDto onboardingRequestDto){
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return userService.addPhotos(hashcodeInt,onboardingRequestDto);
    }

    /**
     * Método que lista un usuario de la base de datos.
     * @param hashcode Hashcode del usuario a listar.
     * @return Usuario solicitado.
     */
    @GetMapping("/users/{hashcode}")
    public ResponseEntity<?>  getUser(@PathVariable String hashcode){
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return userService.getUserByHashcode(hashcodeInt);
    }
}
