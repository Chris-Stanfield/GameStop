package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		// provides meta data on the API service
		info = @Info(
					title = "GameStop API", // title of the Documentation page
					version = "1.0",	// version of your API
					description = "API that allows you to get, create, update, and delete video games.",
					contact = @Contact(name = "Chris", email = "chris.stanfield2021@gmail.com"),
					license = @License(name = "GameStop License v1.0", url = "https://www.gamestop.com/"),
					termsOfService = "https://www.gamestop.com/disclaimer.html" // must be a url
				),
		externalDocs = @ExternalDocumentation(description = "More info on the GameStop API", url = "https://www.gamestop.com/")
		

)
public class GameStopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStopApplication.class, args);
	}

}
