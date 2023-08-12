//package com.erp.autenticador.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.parameters.Parameter;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.customizers.OperationCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .info(new Info().title("API ACESSO").version("1.0"));
//    }
//
//    //    @Bean
////    public OpenAPI customOpenAPI() {
////        return new OpenAPI()
////                .components(new Components().addSecuritySchemes("bearerAuth",
////                        new SecurityScheme()
////                                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
////                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
////    }
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
//}
