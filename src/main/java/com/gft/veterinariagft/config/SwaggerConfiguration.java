
package com.gft.veterinariagft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;



@Configuration
public class SwaggerConfiguration {

	private static final String AUTHORIZATION_KEY = "Authorization";

	@Value("${info.app.version}")
	private String version;

	@Value("${springdoc.description}")
	private String description;

	@Value("${springdoc.info.title}")
	private String title;
	
	@Bean
	 OpenAPI infoApi() {
		Info info = new Info();
		
		SecurityRequirement securityItem = new SecurityRequirement();
		securityItem.addList(AUTHORIZATION_KEY);

		Components components = new Components();
		components.addSecuritySchemes(AUTHORIZATION_KEY, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));

		return new OpenAPI().components(components)
				.addSecurityItem(securityItem)
				.info(info.description(description).version(version).title(title));
	}

}
