package ob.proyecto.validacion.services.hashcode;

import org.springframework.http.ResponseEntity;

/**
 * Interfaz del servicio de hashcode
 */
public interface HashCodeService {

    ResponseEntity<?> update(String username);
    ResponseEntity<?> validate(Integer hash);

}
