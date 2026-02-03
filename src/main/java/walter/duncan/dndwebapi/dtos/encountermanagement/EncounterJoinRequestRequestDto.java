package walter.duncan.dndwebapi.dtos.encountermanagement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// Yes the naming on this is unfortunate...
public record EncounterJoinRequestRequestDto(
        @NotNull Long characterId,
        @Min(1) int initiative
) { }