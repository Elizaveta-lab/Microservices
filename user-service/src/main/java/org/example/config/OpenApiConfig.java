package org.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server gatewayServer = new Server();
        gatewayServer.setUrl("http://localhost:8080");
        gatewayServer.setDescription("API Gateway (localhost:8080)");

        return new OpenAPI()
                .servers(List.of(gatewayServer))
                .info(new Info()
                        .title("User Service API")
                        .description("Микросервис управления пользователями с событиями в Kafka")
                        .version("1.0"));
    }
}
