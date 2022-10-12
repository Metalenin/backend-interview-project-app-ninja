package com.ninjaone.backendinterviewproject;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class OpenApiConfig {
    private static final String AUTH0 = "Auth0";
    private static final String BEARER = "Bearer";

    @Value("${springdoc.swagger-ui.oauth.authorization-url}")
    private String authorizationUrl;
    @Value("${springdoc.swagger-ui.oauth.token-url}")
    private String tokenUrl;
    @Value("${springdoc.swagger-ui.oauth2-redirect-url}")
    private String oauth2RedirectUrl;
    @Value("${server.servlet.context-path}")
    private String serverServletContextPath;

    @Bean
    public OpenAPI springOpenAPI() {

        return new OpenAPI()
                .addServersItem(new Server().url(getUrl()))
                .info(new Info().title("TalentGenius TrueService API")
                        .description("TrueYou + TrueCompany PoC application")
                        .version("v0.0.1")
                        .license(new License().name("Software license").url("http://talentgenius.io")))
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki Documentation")
                        .url("https://talentgenius.io/docs"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(BEARER)
                        .addList(AUTH0)
                )
                .components(new Components()
                        .addSecuritySchemes(AUTH0, new SecurityScheme()
                                .name("Cognito")
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(
                                        new OAuthFlows()
                                                .authorizationCode(
                                                        new OAuthFlow()
                                                                .authorizationUrl(authorizationUrl)
                                                                .tokenUrl(tokenUrl)

                                                )

                                )
                        )
                        .addSecuritySchemes(BEARER, new SecurityScheme()
                                .name("Access Token")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

    private String getUrl() {
        URI uri = URI.create(oauth2RedirectUrl);
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme(uri.getScheme())
                .host(uri.getHost())
                .path(serverServletContextPath)
                .port(uri.getPort());
        return builder.toUriString();
    }
}



