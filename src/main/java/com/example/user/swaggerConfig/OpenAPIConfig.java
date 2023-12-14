package com.example.user.swaggerConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class OpenAPIConfig {

  @Value("${apc91.openapi.dev-url}")
  private String devUrl;

  @Value("${apc91.openapi.prod-url}")
  private String prodUrl; 

  @Bean OpenAPI userApi(){
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment"); 

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Contact contact = new Contact();
    contact.setEmail("penacastilloaberto32@gmail.com");
    contact.setName("APC91");
    contact.setUrl("https://www.albertopena.com");
    
    Info info = new Info()
      .title("User API")
      .version("1.0")
      .contact(contact)
      .description("This API exposes endpoints to users")
      .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
 
}
