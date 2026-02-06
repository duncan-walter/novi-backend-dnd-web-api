package walter.duncan.dndwebapi.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This took some time to figure out, there is no clear examples or unified documentation on how to configure a OAuth2.0 for Swagger.
// The following resources were used (and some trail and error) to get this to work:
// https://springdoc.org/ (contains information about configuring the dependency)
// https://swagger.io/specification/ (contains language-agnostic information about Swagger)
// https://swagger.io/docs/specification/v3_0/authentication/oauth2/
// https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html (contains information about available annotations of io.swagger.v3)
// https://javadoc.io/doc/io.swagger.core.v3/swagger-models/latest/index.html
// io.swagger.v3.oas.models were used in this because of the @Value injections, but the same can also be achieved with io.swagger.v3.oas.annotations
@Configuration
public class SwaggerConfig {
    @Value("${authorizationUrl}")
    private String authorizationUrl;

    @Value("${tokenUrl}")
    private String tokenUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dungeons & Dragons web-API")
                        .version("v1")
                        .description("""
## Summary
This web-API provides endpoints for managing characters, encounters, weapons, and equipment in a D&D game.

## Roles & Permissions
- **Player**: Can manage their own characters (CRUD, including portraits), read weapons and equipment, send join requests to encounters, and read encounters.
- **Dungeon Master**: Can manage characters and portraits, read weapons and equipment, create and read encounters, and directly add participants to encounters.
- **Admin**: Can specifically CRUD weapons and equipment.

Authentication is done via OAuth2.0 / OpenID Connect (Keycloak).

## Test accounts
|Role|Username|Password|Notes|
|-|-|-|-|
|Player|player1|player1|Has 5 characters seeded|
|Dungeon Master|dungeon-master1|dungeon-master1|Has 1 encounter seeded|
|Admin|admin1|admin1|-|
"""
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("OAuth2.0", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(authorizationUrl)
                                                .tokenUrl(tokenUrl)
                                        )
                                )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("OAuth2.0"));
    }
}