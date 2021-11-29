package ob.proyecto.validacion.services;

import ob.proyecto.validacion.dto.*;
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

/**
 * Implementación de la interfaz UserService.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UploadImageCloudinaryServiceImpl uploadService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UploadImageCloudinaryServiceImpl uploadService) {
        this.userRepository =  userRepository;
        this.roleRepository = roleRepository;
        this.uploadService = uploadService;
    }

    /**
     * Método que crea un nuevo usuario en la base de datos.
     * @param userDto Datos del nuevo usuario.
     * @return Nuevo usuario creado.
     */
    @Override
    public ResponseEntity<?> register(UserDto userDto) {
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

        return ResponseEntity.ok(new UserResponseDto("¡Usuario registrado satisfactoriamente!", user));
    }

    /**
     * Método que modifica el usuario, añadiéndole el número de teléfono
     * y dos direcciones url de dos fotografías alojadas en la nube.
     *
     * @param onboardingRequestDto Los dos archivos de imágen.
     * @return Usuario modificado.
     */
    @Override
    public ResponseEntity<?> addPhotos(String username, OnboardingRequestDto onboardingRequestDto) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡Usuario con nombre de usuario " + username +  " no existe!"));


        try {
            user.get().setUrlDni1(uploadService.uploadImage(onboardingRequestDto.getPhoto1()));
            user.get().setUrlDni2(uploadService.uploadImage(onboardingRequestDto.getPhoto2()));
            userRepository.save(user.get());

        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .ok(new UserResponseDto("Teléfono y fotos añadidas.", user.get()));
    }

    /**
     * Método que permite validar a un usuario.
     *
     * @param username Nombre de usuario del usuario a validar.
     * @return Usuario validado.
     */
    @Override
    public ResponseEntity<?> validate(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡Usuario con nombre de usuario " + username + " no existe!"));

        User nUser = user.get();

        if (!nUser.isValidated()){
            nUser.setValidated(true);
            userRepository.save(nUser);
        }

        return ResponseEntity
                .ok( new UserResponseDto("Usuario con nombre de usuario " + username + " validado.", nUser));
    }

    /**
     * Método que devuelve un usuario si existe en la base de datos.
     * @param username Nombre de usuario del usuario a devolver.
     * @return Usuario solicitado.
     */
    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡Usuario con nombre de usuario " + username + " no existe!"));

        return ResponseEntity.ok(new UserResponseDto("Datos del usuario", user.get()));
    }

    /**
     * Método que devuelve todos los usuarios en la base de datos.
     * @return Lista de usuarios.
     */
    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(new UserListResponseDto("Listado de usuarios", users));
    }
}
