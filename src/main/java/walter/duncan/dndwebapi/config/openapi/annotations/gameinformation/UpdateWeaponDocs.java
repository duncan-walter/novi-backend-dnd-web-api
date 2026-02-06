package walter.duncan.dndwebapi.config.openapi.annotations.gameinformation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.config.openapi.examples.gameinformation.GameInformationExampleProvider;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Updates a weapon (Admin)",
        description = "Updates an existing weapon. The request body must contain valid weapon attributes.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = WeaponRequestDto.class,
                                example = GameInformationExampleProvider.UPDATE_WEAPON
                        )
                )
        )
)
public @interface UpdateWeaponDocs { }