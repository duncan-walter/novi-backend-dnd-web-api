package walter.duncan.dndwebapi.config.openapi.annotations.gameinformation;

import java.lang.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag(
        name = "Game information",
        description = "Endpoints for creating, retrieving, updating and deleting game information such as equipment and weapons"
)
public @interface GameInformationTag { }