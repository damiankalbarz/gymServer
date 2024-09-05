package com.example.gymServer;

import com.example.gymServer.authorization.auth.AuthenticationService;
import com.example.gymServer.authorization.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static com.example.gymServer.authorization.user.Role.ADMIN;
import static com.example.gymServer.authorization.user.Role.MANAGER;


@EnableWebSecurity
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class GymServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymServerApplication.class, args);
	}

/*
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}*/
}
