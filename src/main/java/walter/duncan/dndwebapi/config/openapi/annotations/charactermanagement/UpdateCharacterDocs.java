package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.config.openapi.examples.charactermanagement.CharacterExampleProvider;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Update a character",
        description = "Updates an existing character identified by ID. The character must belong to the authenticated user.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = CharacterRequestDto.class,
                                example = CharacterExampleProvider.UPDATE_CHARACTER
                        )
                )
        )
)
public @interface UpdateCharacterDocs { }