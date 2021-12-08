package ob.proyecto.validacion.controller;

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

    @GetMapping("/validate/{hashcode}")
    public ResponseEntity<?> validate(@PathVariable Integer hashcode){
        return hashCodeService.validate(hashcode);
    }
}

