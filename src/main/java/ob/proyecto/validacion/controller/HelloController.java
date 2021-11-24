package ob.proyecto.validacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping({"/", "/hola", "/hello"})
    public String hola() {
        return "index.html";
    }
}
