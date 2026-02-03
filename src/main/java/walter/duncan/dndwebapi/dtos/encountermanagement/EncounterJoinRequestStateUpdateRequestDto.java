package walter.duncan.dndwebapi.dtos.encountermanagement;

import jakarta.validation.constraints.NotNull;

// Request dto used for approving and declining join requests
public record EncounterJoinRequestStateUpdateRequestDto(
        @NotNull String state
) { }