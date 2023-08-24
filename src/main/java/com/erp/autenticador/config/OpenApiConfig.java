package com.erp.autenticador.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI springShopOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("Microserviço api-erp-rh")
                        .description("Microserviço para gerenciamento de RH")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org%22%29%29/")))
                                .externalDocs(new ExternalDocumentation()
                                        .description("SpringShop Wiki Documentation")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0%22"))
                                        .addSecurityItem(new SecurityRequirement()
                                                .addList(securitySchemeName))
                                        .components(new Components()
                                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                                        .name(securitySchemeName)
                                                        .type(SecurityScheme.Type.HTTP)
                                                        .scheme("bearer")
                                                        .bearerFormat("JWT")));
    }


//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .info(new Info().title("API ACESSO").version("1.0"));
//    }

    //    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth",
//                        new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
//    }
//    @Bean
//    public OperationCustomizer operationCustomizer() {
//        return (operation, handlerMethod) -> {
//            operation.addParametersItem(new Parameter()
//                    .name("Authorization")
//                    .description("Header para token jwt")
//                    .in("header")
//                    .required(false)
//                    .schema(new io.swagger.v3.oas.models.media.Schema().type("string")));
//            return operation;
//        };
//    }
}
