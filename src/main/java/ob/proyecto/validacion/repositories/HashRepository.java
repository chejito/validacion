package ob.proyecto.validacion.repositories;

import ob.proyecto.validacion.entities.HashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Respositorio de la entidad HashCode
 */
@Repository
public interface HashRepository extends JpaRepository<HashCode, Long> {

    HashCode createHashCode(Integer hash);
    HashCode getHashCode(Integer hash);
    HashCode getHashCodeByUsername(String username);

}
