package ob.proyecto.validacion.services.hashcodes;

import ob.proyecto.validacion.entities.HashCode;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz del servicio de hashcode
 */
public interface HashCodeService {

    //ResponseEntity<?> create(String username);
    ResponseEntity<?> update(String username);
    ResponseEntity<?> validate(Integer hash);

}
