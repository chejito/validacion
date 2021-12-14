package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingRequestDto;
import ob.proyecto.validacion.dto.OnePhotoRequestDto;
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
     * Método que añade dos ficheros de imágenes a un usuario y devuelve sus direcciones URL.
     * @param hashcode El hashcode que identifica al usuario.
     * @param onboardingRequestDto DTO que incluye los ficheros de imágenes.
     * @return En caso positivo, las URLs resultado de la subida de los archivos y un mensaje.
     * En caso negativo, un mensaje de error.
     */
    @PutMapping("/photos/{hashcode}")
    public ResponseEntity<?> addPhotos(@PathVariable String hashcode, OnboardingRequestDto onboardingRequestDto){
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return userService.addPhotos(hashcodeInt,onboardingRequestDto);
    }

    /**
     * Método que añade un fichero de imágen a un usuario y devuelve su dirección URL.
     *
     * @param hashcode El hashcode que identifica al usuario.
     * @param onePhotoRequestDto DTO que incluye el fichero de imágen.
     * @return En caso positivo, la URL resultado de la subida del archivo y un mensaje.
     * En caso negativo, un mensaje de error.
     */
    @PostMapping("/uploadphoto/{hashcode}")
    public ResponseEntity<?> addOnePhoto(@PathVariable String hashcode, OnePhotoRequestDto onePhotoRequestDto){
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return userService.addOnePhoto(hashcodeInt,onePhotoRequestDto);
    }

    /**
     * Método que devuelve un usuario de la base de datos.
     * @param hashcode El hashcode que identifica al usuario.
     * @return Usuario solicitado.
     */
    @GetMapping("/users/{hashcode}")
    public ResponseEntity<?>  getUser(@PathVariable String hashcode){
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return userService.getUserByHashcode(hashcodeInt);
    }
}
