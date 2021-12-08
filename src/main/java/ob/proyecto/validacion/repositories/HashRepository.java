package ob.proyecto.validacion.repositories;

import ob.proyecto.validacion.entities.HashCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Respositorio de la entidad HashCode
 */
public interface HashRepository extends JpaRepository<HashCode, Long> {

    HashCode getByHash(Integer hash);

}
