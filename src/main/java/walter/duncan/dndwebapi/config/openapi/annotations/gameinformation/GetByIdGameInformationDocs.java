package walter.duncan.dndwebapi.config.openapi.annotations.gameinformation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.swagger.v3.oas.annotations.Operation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Operation(
        summary = "Get game information by ID",
        description = "Retrieves game information of a certain type (equipment or weapons) by its unique identifier."
)
public @interface GetByIdGameInformationDocs { }