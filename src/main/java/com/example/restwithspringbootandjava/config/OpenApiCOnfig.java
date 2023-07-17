package com.example.restwithspringbootandjava.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Implementado o Swagger para documentação da API. Apos a importação da dependência, foi criada a
classe de configuração que permite acessar a documentação gerada automaticamente pelo Swagger.
Para acessar basta digitar no navegador: http://localhost:8080/swagger-ui/index.html ou http://localhost:8080/v3/api-docs
* */

@Configuration
public class OpenApiCOnfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java and Spring Boot")
                        .version("v1")
                        .description("Some description...")
                        .termsOfService("http://urlQualquer.com.br")
                        .license(new License().name("Apache 2.0")
                                .url("http://urlQualquer.com.br")));


    }


}
