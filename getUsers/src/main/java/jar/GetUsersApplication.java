package jar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class GetUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetUsersApplication.class, args);
	}
	
	
	public void addCROSSupport(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET","POST");
		
		
	}
	
	

}
