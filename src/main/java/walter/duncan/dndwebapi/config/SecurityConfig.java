package walter.duncan.dndwebapi.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!test") // This class should not be executed during test runs (tries to connect to keycloak)
public class SecurityConfig {
    @Value("${issuer-uri}")
    private String issuer;

    @Value("${audience}")
    private String audience;

    @Value("${client-id}")
    private String clientId;

    // NOTE: Endpoints are secured very explicitly. This is to make the global security future-proof when new roles or endpoints are added.
    private static void addGlobalEndpointSecurity(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize
                // Weapon & Equipment endpoints
                .requestMatchers(HttpMethod.GET, "/weapons", "/weapons/{id}").hasAnyRole("ADMIN", "DUNGEON_MASTER", "PLAYER")
                .requestMatchers(HttpMethod.POST,"/weapons", "/equipment").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/weapons/{id}", "/equipment/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/weapons/{id}", "/equipment/{id}").hasRole("ADMIN")

                // Encounter endpoints
                .requestMatchers(HttpMethod.GET, "/encounters").hasAnyRole("DUNGEON_MASTER", "PLAYER")
                .requestMatchers(HttpMethod.POST, "/encounters", "/encounters/{id}/participants").hasRole("DUNGEON_MASTER")
                .requestMatchers(HttpMethod.PATCH, "/encounters/{id}").hasRole("DUNGEON_MASTER")

                .requestMatchers(HttpMethod.GET, "/encounters/{id}/join-requests").hasRole("DUNGEON_MASTER")
                .requestMatchers(HttpMethod.POST, "/encounters/{id}/join-requests").hasRole("PLAYER")
                .requestMatchers(HttpMethod.PATCH, "/encounters/{encounterId}/join-requests/{id}").hasRole("DUNGEON_MASTER")

                // Character endpoints
                .requestMatchers("/characters/**").hasAnyRole("DUNGEON_MASTER", "PLAYER")

                // Authenticated
                .requestMatchers("/**").authenticated() // Any user is required to be authenticated for any endpoint

                // Default
                .anyRequest().denyAll();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> { })
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                .decoder(jwtDecoder())
                        ))
                .authorizeHttpRequests(SecurityConfig::addGlobalEndpointSecurity)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new Converter<>() {
            @Override
            public Collection<GrantedAuthority> convert(Jwt source) {
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                for (String authority : getAuthorities(source)) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                }

                return grantedAuthorities;
            }

            private List<String> getAuthorities(Jwt jwt) {
                Map<String, Object> resourceAcces = jwt.getClaim("resource_access");

                if (resourceAcces != null) {
                    if (resourceAcces.get(clientId) instanceof Map) {
                        Map<String, Object> client = (Map<String, Object>) resourceAcces.get(clientId);

                        if (client != null && client.containsKey("roles")) {
                            return (List<String>) client.get("roles");
                        }
                    }
                }
                return new ArrayList<>();
            }
        });

        return jwtAuthenticationConverter;
    }

    private JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> audienceValidator = new JwtAudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}