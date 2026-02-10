package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Upload or replace character portrait (Doesn't work through Swagger, try Postman)",
        description = "Uploads or replaces the portrait image for an existing character. Supported formats: PNG, JPG, JPEG, GIF. The character must belong to the authenticated user.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                        schema = @Schema(
                                type = "string",
                                format = "binary",
                                description = "Portrait image file"
                        )
                )
        )
)
public @interface UploadCharacterPortraitDocs { }