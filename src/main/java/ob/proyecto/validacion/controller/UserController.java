package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.LoginUser;
import ob.proyecto.validacion.dto.UserDto;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }

    @PostMapping("/registrate")
    public ResponseEntity<User> registrate(@RequestBody UserDto userDto){

        return userService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUser loginUser){
        List<User> list = userService.findAll().getBody();

        for (User user : list){
            if (user.getUsername().equalsIgnoreCase(loginUser.getUsername())){
                if (user.getPassword().equalsIgnoreCase(loginUser.getPassword())){
                    return ResponseEntity.ok("Usuario logado");
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
