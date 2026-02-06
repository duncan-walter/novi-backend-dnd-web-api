package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get character portrait",
        description = "Retrieves the portrait image associated with a character. The image is returned using its original MIME type and rendered inline."
)
public @interface GetCharacterPortraitDocs { }