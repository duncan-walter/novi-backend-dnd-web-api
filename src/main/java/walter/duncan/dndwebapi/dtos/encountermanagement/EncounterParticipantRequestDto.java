package walter.duncan.dndwebapi.dtos.encountermanagement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EncounterParticipantRequestDto(
        @NotNull Long characterId,
        @Min(1) int initiative
) { }