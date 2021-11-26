package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.OnboardingDto;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.dto.ValidationDto;
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
import java.util.Optional;
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

    @Override
    public ResponseEntity<MessageResponse> register(UserDto userDto) {
        List<User> list = userRepository.findAll();

        for (User user : list){
            // Comprobacion 1: username
            if (user.getUsername().equalsIgnoreCase(userDto.getUsername())){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: ¡El username " + userDto.getUsername() + " ya esta registrado!"));
            }

            // Comprobacion 2: fullname
            if (user.getFullname().equalsIgnoreCase(userDto.getFullname())){
                return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + userDto.getFullname() + " ya está registrado!"));
            }

            // Comprobacion 3: email
            if (user.getEmail().equalsIgnoreCase(userDto.getEmail()))
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: ¡" + userDto.getEmail() + " ya está registrado"));

        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getByName("USER"));
        userDto.setRoles(roles);
        User user = userDto.getUserFromDto();
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("¡Usuario registrado satisfactoriamente!"));
    }

    @Override
    public ResponseEntity<MessageResponse> addPhotosAndPhone(OnboardingDto onboardingDto) {

        Optional<User> user = userRepository.findByUsername(onboardingDto.getUsername());

        if (user == null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + onboardingDto.getUsername() + " no existe!"));

        user.get().setPhone(onboardingDto.getPhone());
        user.get().setDni1(onboardingDto.getDni1());
        user.get().setDni2(onboardingDto.getDni2());

        userRepository.save(user.get());

        return ResponseEntity
                .ok( new MessageResponse("DNI y phone actualizados"));
    }

    @Override
    public ResponseEntity<MessageResponse> validate(ValidationDto validationDto) {
        Optional<User> user = userRepository.findByUsername(validationDto.getUsername());

        if (user == null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + validationDto.getUsername() + " no existe!"));

        user.get().setValidated(true);
        userRepository.save(user.get());

        return ResponseEntity
                .ok( new MessageResponse("¡Usuario " + user.get().getUsername() + " validado!"));
    }
}
