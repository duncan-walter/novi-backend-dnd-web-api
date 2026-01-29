package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record WeaponCharacterInventoryItemRequestDto(
        String type,
        Long referenceId,
        int quantity
) implements CharacterInventoryItemRequestDto { }