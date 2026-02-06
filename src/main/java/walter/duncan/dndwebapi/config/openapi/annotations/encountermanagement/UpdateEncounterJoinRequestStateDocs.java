package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestStateUpdateRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Approve or decline an encounter join request (Dungeon master)",
        description = "Approve or decline an encounter join request on an encounter. The join request id in the url must reference an encounter owned by the dungeon master and the request body must contain a valid state property with a value of one of the following: 'approved, declined'.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema( implementation = EncounterJoinRequestStateUpdateRequestDto.class),
                        examples = {
                                @ExampleObject(name = "Approve join request", summary = "Start the encounter", value = "{ \"state\" : \"approved\" }"),
                                @ExampleObject(name = "Decline join request", summary = "Advance to the next turn", value = "{ \"state\" : \"declined\" }")
                        }
                )
        )
)
public @interface UpdateEncounterJoinRequestStateDocs { }