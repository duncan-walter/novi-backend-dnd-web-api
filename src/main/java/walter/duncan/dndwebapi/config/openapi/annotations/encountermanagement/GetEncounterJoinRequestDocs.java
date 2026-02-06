package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get all encounters encounter join requests (Dungeon master)",
        description = "Returns a list of all encounter join requests of an encounter."
)
public @interface GetEncounterJoinRequestDocs { }