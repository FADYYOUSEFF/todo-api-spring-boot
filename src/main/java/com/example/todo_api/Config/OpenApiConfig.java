package com.example.todo_api.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "fady",
                        email= "fadyyoussef4444@gmail.com"
                ),
                title = "Todo App Spring Boot Rest Api",
                description = "OpenApi document for TodoApp api",
                version = "1.0"
        )

)
@Servers(
        @Server(
                description = "todo api",
                url = "http://localhost:9090"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        scheme="bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
