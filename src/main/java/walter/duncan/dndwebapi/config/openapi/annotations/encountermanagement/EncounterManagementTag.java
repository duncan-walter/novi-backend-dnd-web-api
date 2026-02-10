package walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement;

import java.lang.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag(
        name = "Encounter management",
        description = "Endpoints for managing encounters, participants, and join requests. Dungeon Masters can manage all aspects; players can view encounters and create join requests using their own characters."
)
public @interface EncounterManagementTag { }