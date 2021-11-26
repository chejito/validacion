package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.OnboardingDto;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.repositories.RoleRepository;
import ob.proyecto.validacion.security.jwt.JwtTokenUtil;
import ob.proyecto.validacion.security.payload.JwtResponse;
import ob.proyecto.validacion.security.payload.LoginRequest;
import ob.proyecto.validacion.security.payload.MessageResponse;
import ob.proyecto.validacion.security.payload.RegisterRequest;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authManager,
                          UserServiceImpl userService,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil,
                          RoleRepository roleRepository){
        this.authManager = authManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {

        //Comprueba que no hay campos vacios
        if (signUpRequest.getName() == null ||
            signUpRequest.getSurename() == null ||
            signUpRequest.getEmail() == null ||
            signUpRequest.getUsername() == null ||
            signUpRequest.getPassword() == null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: es necesario enviar los campos name, surename, email, " +
                            "username y password"));

        UserDto userDto = new UserDto(signUpRequest.getName() + " " + signUpRequest.getSurename(),
                signUpRequest.getEmail(),
                signUpRequest.getUsername(),
                signUpRequest.getPassword());

        ResponseEntity<MessageResponse> result = userService.register(userDto);

        return result;
    }
}
