package ob.proyecto.validacion.repositories;

import ob.proyecto.validacion.entities.HashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Respositorio de la entidad HashCode
 */
@Repository
public interface HashCodeRepository extends JpaRepository<HashCode, Long> {

}
