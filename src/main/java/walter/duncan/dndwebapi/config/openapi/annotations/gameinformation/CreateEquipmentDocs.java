package walter.duncan.dndwebapi.config.openapi.annotations.gameinformation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import walter.duncan.dndwebapi.config.openapi.examples.gameinformation.GameInformationExampleProvider;
import walter.duncan.dndwebapi.dtos.gameinformation.equipment.EquipmentRequestDto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Create an equipment item (Admin)",
        description = "Creates new equipment item. The request body must contain valid equipment attributes.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = EquipmentRequestDto.class,
                                example = GameInformationExampleProvider.CREATE_EQUIPMENT
                        )
                )
        )
)
public @interface CreateEquipmentDocs { }