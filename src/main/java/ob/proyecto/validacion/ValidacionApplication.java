package ob.proyecto.validacion;

import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ValidacionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ValidacionApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);

		User user = new User();
		user.setId(null);
		user.setUsername("user1");
		user.setEmail("user@user2.com");
		user.setPassword("user");
		user.setRole("USER");
		userRepository.save(user);

	}

}
