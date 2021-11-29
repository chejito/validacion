package ob.proyecto.validacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de la página de inicio
 */
@Controller
public class HelloController {

    @GetMapping({"/", "/hola", "/hello"})
    public String hola() {
        return "index.html";
    }
}
