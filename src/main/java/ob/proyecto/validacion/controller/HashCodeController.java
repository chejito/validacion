package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.HashCodeDto;
import ob.proyecto.validacion.services.hashcodes.HashCodeServiceImpl;
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

    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateHashCode(@PathVariable String username){
        return hashCodeService.update(username);
    }

    @PostMapping("/validate/{hashcode}")
    public ResponseEntity<?> validate(@PathVariable String hashcode) {
        Integer hashcodeInt = Integer.parseInt(hashcode);

        return hashCodeService.validate(hashcodeInt);
    }
}

