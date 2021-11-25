package ob.proyecto.validacion.services;

import ob.proyecto.validacion.entities.Role;
import ob.proyecto.validacion.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository =  roleRepository;
    }

    @Override
    public Role findRole(String name) {

        List<Role> roles = roleRepository.findAll();

        for (Role role : roles){
            if (role.getName().equalsIgnoreCase(name))
                return role;
        }
        return null;
    }
}
