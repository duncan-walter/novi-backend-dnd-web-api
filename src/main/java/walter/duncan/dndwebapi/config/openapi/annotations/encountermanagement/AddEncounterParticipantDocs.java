package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import walter.duncan.dndwebapi.config.openapi.examples.encountermanagement.EncounterExampleProvider;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestRequestDto;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Add an encounter participant (Dungeon master)",
        description = "Creates a new participant on an encounter. The request body must contain a valid reference to an owned character by the authenticated user and contain valid attributes.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = EncounterJoinRequestRequestDto.class,
                                example = EncounterExampleProvider.ADD_PARTICIPANT
                        )
                )
        )
)
public @interface AddEncounterParticipantDocs { }