package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.LoginUser;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserController(UserServiceImpl userService, BCryptPasswordEncoder encoder){
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto){

        return userService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUser loginUser){
        List<User> list = userService.findAll().getBody();

        for (User user : list){
            if (user.getUsername().equalsIgnoreCase(loginUser.getUsername())){
                // Desencripta la contraseña del usuario para comparacion
                if (encoder.matches(loginUser.getPassword(), user.getPassword())){
                    return ResponseEntity.ok("Usuario logado");
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
