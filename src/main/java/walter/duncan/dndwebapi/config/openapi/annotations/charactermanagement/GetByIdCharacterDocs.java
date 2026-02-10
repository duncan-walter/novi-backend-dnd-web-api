package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import io.swagger.v3.oas.annotations.Operation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get character by ID",
        description = "Retrieves a single character by its unique identifier. The character must belong to the authenticated user."
)
public @interface GetByIdCharacterDocs { }