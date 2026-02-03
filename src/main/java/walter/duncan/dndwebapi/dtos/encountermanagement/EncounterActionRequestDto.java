package walter.duncan.dndwebapi.dtos.encountermanagement;

import jakarta.validation.constraints.NotNull;

public record EncounterActionRequestDto(
        @NotNull String action
) { }