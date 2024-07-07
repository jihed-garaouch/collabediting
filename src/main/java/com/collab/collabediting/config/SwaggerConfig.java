package com.collab.collabediting.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Collab Editing API")
                .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("Github Oauth2"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("github-oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                               ));

    }
}
