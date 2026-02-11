package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CustomCharacterInventoryItemRequestDto(
        String type,
        @NotBlank @Size(max = 50) String name,
        @Size(max = 2500) String description,
        @PositiveOrZero Long valueInCopperPieces,
        @PositiveOrZero Double weightInLbs,
        @PositiveOrZero int quantity
) implements CharacterInventoryItemRequestDto { }