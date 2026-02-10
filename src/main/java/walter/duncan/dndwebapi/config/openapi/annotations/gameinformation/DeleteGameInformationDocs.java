package walter.duncan.dndwebapi.config.openapi.annotations.gameinformation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Deletes an game information item (Admin)",
        description = "Deletes an existing game information item. A game information item cannot be deleted if it's referenced in any character's inventory."
)
public @interface DeleteGameInformationDocs { }