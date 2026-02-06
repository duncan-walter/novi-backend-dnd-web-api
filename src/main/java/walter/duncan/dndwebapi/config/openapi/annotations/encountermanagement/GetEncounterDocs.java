package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get all encounters",
        description = "Returns a list of all encounters with it's participants embedded"
)
public @interface GetEncounterDocs { }