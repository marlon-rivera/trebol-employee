package com.trebol.auth.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Trebol los mejores forever friends"
                ),
                description = "OpenApi documentation for microservice of employee",
                title = "OpenApi Microservice employee",
                version = "0.0.1"
        )
)
public class OpenApiConfig {
}
