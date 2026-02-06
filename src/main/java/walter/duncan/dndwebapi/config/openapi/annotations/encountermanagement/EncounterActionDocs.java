package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterActionRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Execute an action on an encounter (Dungeon master)",
        description = "Executes an action on an encounter owned by the authenticated user. The request body must contain a valid action property with a value of one of the following: 'start, advance-turn, close'.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = EncounterActionRequestDto.class),
                        examples = {
                                @ExampleObject(name = "Start encounter", summary = "Start the encounter", value = "{ \"action\" : \"start\" }"),
                                @ExampleObject(name = "Advance turn", summary = "Advance to the next turn", value = "{ \"action\" : \"advance-turn\" }"),
                                @ExampleObject(name = "Stop encounter", summary = "Stop the encounter", value = "{ \"action\" : \"close\" }")
                        }
                )
        )
)
public @interface EncounterActionDocs { }