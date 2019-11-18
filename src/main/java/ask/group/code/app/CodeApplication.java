package ask.group.code.app;

import ask.group.code.controller.EmployeeController;
import ask.group.code.repository.EmployeeDataLoader;
import ask.group.code.security.SecurityConfiguration;
import ask.group.code.service.AuthorizedUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan( basePackages = {"ask.group.code.model"} )
@ComponentScan(basePackageClasses = {EmployeeController.class, SecurityConfiguration.class, AuthorizedUserDetailsService.class, EmployeeDataLoader.class})
public class CodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

}
