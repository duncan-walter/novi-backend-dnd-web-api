package walter.duncan.dndwebapi.config.openapi.annotations.charactermanagement;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag(
        name = "Character management",
        description = "Endpoints for creating, retrieving, updating, deleting, and managing character portraits owned by the authenticated user."
)
public @interface CharacterManagementTag { }