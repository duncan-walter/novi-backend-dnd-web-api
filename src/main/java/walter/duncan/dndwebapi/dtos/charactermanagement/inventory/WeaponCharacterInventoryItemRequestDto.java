package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record WeaponCharacterInventoryItemRequestDto(
        String type,
        @NotNull Long referenceId,
        @PositiveOrZero int quantity
) implements CharacterInventoryItemRequestDto { }