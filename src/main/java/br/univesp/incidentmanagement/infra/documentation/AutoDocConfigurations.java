package br.univesp.incidentmanagement.infra.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Gestão de Ocorrências")
                        .description("Sistema de Gestão de Ocorrências da Unidade Escolar: 0.00 - SENAI")
                        .contact(new Contact()
                                .name("Serviço Nacional de Aprendizagem Industrial - SENAI")
                                .email("carlos.moliveira@sp.senai.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://gestaodeocorrenciassenai000.com.br/api/license")));
    }
}