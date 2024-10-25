package com.example.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("API Title").version("1.0"))
				.addSecurityItem(new SecurityRequirement().addList("Token"))
				.components(
					new io.swagger.v3.oas.models.Components()
	                    .addSecuritySchemes("Token", new SecurityScheme()
	                    .type(SecurityScheme.Type.APIKEY)
	                    .in(SecurityScheme.In.HEADER)
	                    .name("Authorization")));
	}
	
}
