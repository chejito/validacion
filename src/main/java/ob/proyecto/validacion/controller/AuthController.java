package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.RoleRepository;
import ob.proyecto.validacion.repositories.UserRepository;
import ob.proyecto.validacion.security.jwt.JwtTokenUtil;
import ob.proyecto.validacion.security.payload.JwtResponse;
import ob.proyecto.validacion.security.payload.LoginRequest;
import ob.proyecto.validacion.security.payload.MessageResponse;
import ob.proyecto.validacion.security.payload.RegisterRequest;
import ob.proyecto.validacion.services.hashcode.HashCodeUtils;
import ob.proyecto.validacion.services.user.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final HashCodeUtils utils;

    public AuthController(AuthenticationManager authManager,
                          UserServiceImpl userService,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil,
                          RoleRepository roleRepository,
                          UserRepository userRepository, HashCodeUtils utils){
        this.authManager = authManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    /**
     * Método que permite iniciar sesión a un usuario.
     * @param loginRequest Datos del usuario.
     * @return Token jwt, rol principal, nombre de usuario y código hash..
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);


        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        String roleToSend = "USER";
        String username = loginRequest.getUsername();
        Optional<User> oUuser = userRepository.findByUsername(username);

        Integer hashcode = 0;
        if (oUuser.isPresent()) {
            User user = oUuser.get();
            hashcode = utils.updateHash(user);
        }

        for (GrantedAuthority role : roles) {
            if (role.toString().equals("ADMIN")) {
                roleToSend = role.toString();
            }
        }

        return ResponseEntity.ok(new JwtResponse(jwt, roleToSend, username, hashcode));
    }

    /**
     * Método que permite registrarse a usuario.
     * @param signUpRequest Datos del usuario.
     * @return Respuesta del userService.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest signUpRequest) {

        //Comprueba que no hay campos vacios
        if (signUpRequest.getName() == null ||
            signUpRequest.getSurname() == null ||
            signUpRequest.getEmail() == null ||
            signUpRequest.getUsername() == null ||
            signUpRequest.getPassword() == null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: es necesario enviar los campos name, surname, email, " +
                            "username y password"));

        UserDto userDto = new UserDto(signUpRequest.getName() + " " + signUpRequest.getSurname(),
                signUpRequest.getEmail(),
                signUpRequest.getUsername(),
                signUpRequest.getPassword());

        return userService.register(userDto);
    }
}
