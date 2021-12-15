package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.services.hashcode.HashCodeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para crear y validar hashcodes
 */
@RestController
@RequestMapping("/api/hashcode")
public class HashCodeController {

    private final HashCodeServiceImpl hashCodeService;

    public HashCodeController(HashCodeServiceImpl hashCodeService){
        this.hashCodeService = hashCodeService;
    }

    /**
     * Método que actualiza el código hash de un usuario a partir de su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Respuesta del hashCodeService.
     */
    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateHashCode(@PathVariable String username){
        return hashCodeService.update(username);
    }

    /**
     * Método que valida el código hash de un usuario.
     *
     * @param hashcode Código hash.
     * @return Respuesta del hashCodeService.
     */
    @PostMapping("/validate/{hashcode}")
    public ResponseEntity<?> validate(@PathVariable String hashcode) {
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return hashCodeService.validate(hashcodeInt);
    }
}

