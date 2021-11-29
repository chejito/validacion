package ob.proyecto.validacion.repositories;

import ob.proyecto.validacion.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de la entidad Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);

}
