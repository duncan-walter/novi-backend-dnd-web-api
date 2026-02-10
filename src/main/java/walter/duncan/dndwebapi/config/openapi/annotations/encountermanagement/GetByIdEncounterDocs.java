package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get encounter by ID",
        description = "Retrieves a single encounter by its unique identifier"
)
public @interface GetByIdEncounterDocs { }