package com.dio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager API")
                        .version("1.0")
                        .description("API para gerenciamento de tarefas")
                        .contact(new Contact()
                                .name("Pedro Solozabal")
                                .email("contato@solozabal.com.br")
                                .url("https://github.com/solozabal")));
    }
}