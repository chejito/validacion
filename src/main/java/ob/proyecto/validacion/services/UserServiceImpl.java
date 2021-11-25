package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.Role;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.RoleRepository;
import ob.proyecto.validacion.repositories.UserRepository;
import ob.proyecto.validacion.security.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository =  userRepository;
        this.roleRepository = roleRepository;
    }

//    @Override
//    public ResponseEntity<List<User>> findAll() {
//        List<User> result = userRepository.findAll();
//
//        if (result.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(result);
//    }
//
//    @Override
//    public ResponseEntity<User> findByUsername(String username) {
//        List<User> result = userRepository.findAll();
//
//        if (result.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        for (User user : result){
//            if (user.getUsername().equalsIgnoreCase(username)){
//                return ResponseEntity.ok(user);
//            }
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @Override
//    public boolean fullnameIsUsed(String fullname) {
//        List<User> result = userRepository.findAll();
//
//        if (result.isEmpty())
//            return false;
//
//        for (User user : result){
//            if (user.getFullname().equalsIgnoreCase(fullname)){
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    public ResponseEntity<MessageResponse> register(UserDto userDto) {
        List<User> list = userRepository.findAll();

        for (User user : list){
            // Comprobacion 1: username (email)
            if (user.getUsername().equalsIgnoreCase(userDto.getUsername())){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: ¡El email " + userDto.getUsername() + " ya esta en uso!"));
            }

            // Comprobacion 2: fullname
            if (user.getFullname().equalsIgnoreCase(userDto.getFullname())){
                return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + userDto.getFullname() + " ya está en registrado!"));
            }

        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getByName("USER"));
        userDto.setRoles(roles);
        User user = userDto.getUserFromDto();
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("¡Usuario registrado satisfactoriamente!"));
    }
}
