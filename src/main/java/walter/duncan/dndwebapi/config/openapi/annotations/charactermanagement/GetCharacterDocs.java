package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import io.swagger.v3.oas.annotations.Operation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get all characters",
        description = "Returns a list of all characters owned by the authenticated user."
)
public @interface GetCharacterDocs { }