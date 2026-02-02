package walter.duncan.dndwebapi.dtos.encountermanagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EncounterRequestDto(
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 2500) String description
) { }