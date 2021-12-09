package ob.proyecto.validacion.repositories;

import ob.proyecto.validacion.entities.HashCode;
import ob.proyecto.validacion.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Respositorio de la entidad HashCode
 */
@Repository
public interface HashCodeRepository extends JpaRepository<HashCode, Long> {
    HashCode findByUser(User user);
}
