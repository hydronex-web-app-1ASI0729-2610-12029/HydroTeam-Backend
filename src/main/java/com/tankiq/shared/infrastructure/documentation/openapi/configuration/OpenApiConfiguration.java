package com.tankiq.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Value("${spring.application.name}")
    String applicationName;

    @Value("${spring.application.description}")
    String applicationDescription;

    @Value("${spring.application.version}")
    String applicationVersion;

    @Bean
    public OpenAPI tankiqOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title(this.applicationName)
                        .description(this.applicationDescription)
                        .version(this.applicationVersion)
                        .contact(new Contact()
                                .name("TankIQ Support")
                                .email("support@tankiq.com")
                                .url("https://www.tankiq.com/support"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("TankIQ API Documentation")
                        .url("https://www.tankiq.com/api-docs"));

        openApi.servers(List.of(
                new Server().url("http://localhost:8080").description("Local Development Environment"),
                new Server().url("https://staging-api.tankiq.com").description("Staging Environment"),
                new Server().url("https://api.tankiq.com").description("Production Environment")
        ));
        return openApi;
    }
}
