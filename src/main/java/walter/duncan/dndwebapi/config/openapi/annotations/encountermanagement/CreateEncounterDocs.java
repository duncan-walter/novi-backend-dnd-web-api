package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.config.openapi.examples.encountermanagement.EncounterExampleProvider;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Create an encounter (Dungeon master)",
        description = "Creates a new encounter and assigns ownership to the authenticated user. The request body must contain valid encounter attributes.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = EncounterRequestDto.class,
                                example = EncounterExampleProvider.CREATE_ENCOUNTER
                        )
                )
        )
)
public @interface CreateEncounterDocs { }