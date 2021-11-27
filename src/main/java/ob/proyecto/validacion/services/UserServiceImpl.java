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

    @Override
    public ResponseEntity<?> addPhoto(OnboardingPhotoRequestDto onboardingPhotoRequestDto) {
        Optional<User> user = userRepository.findByUsername(onboardingPhotoRequestDto.getUsername());

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + onboardingPhotoRequestDto.getUsername() + " no existe!"));

        try {
            user.get().setUrlDni1(uploadService.uploadImage(onboardingPhotoRequestDto.getPhoto()));
            userRepository.save(user.get());

        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        //String dniUrl = user.get().getUrlDni1();

        return ResponseEntity
                .ok(new UserResponseDto("Foto añadida.", user.get()));
    }

    @Override
    public ResponseEntity<?> addPhotosAndPhone(OnboardingRequestDto onboardingRequestDto) {

        Optional<User> user = userRepository.findByUsername(onboardingRequestDto.getUsername());

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + onboardingRequestDto.getUsername() + " no existe!"));


        try {
            user.get().setPhone(onboardingRequestDto.getPhone());
            user.get().setUrlDni1(uploadService.uploadImage(onboardingRequestDto.getPhoto1()));
            user.get().setUrlDni2(uploadService.uploadImage(onboardingRequestDto.getPhoto2()));
            userRepository.save(user.get());

        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        //String[] dniUrl = {user.get().getUrlDni1(), user.get().getUrlDni2()};

        return ResponseEntity
                .ok(new UserResponseDto("Teléfono y fotos añadidas.", user.get()));
    }

    @Override
    public ResponseEntity<?> validate(ValidationDto validationDto) {
        Optional<User> user = userRepository.findByUsername(validationDto.getUsername());

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + validationDto.getUsername() + " no existe!"));

        user.get().setValidated(true);
        userRepository.save(user.get());

        return ResponseEntity
                .ok( new UserResponseDto("Usuario " + validationDto.getUsername() + " validado.", user.get()));
    }

    public ResponseEntity<?> getUserDto(ValidationDto validationDto) {
        Optional<User> user = userRepository.findByUsername(validationDto.getUsername());

        if (user.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡" + validationDto.getUsername() + " no existe!"));

        return ResponseEntity.ok(new UserResponseDto("Datos del usuario", user.get()));
    }
}
