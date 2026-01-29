package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record EquipmentCharacterInventoryItemRequestDto(
        String type,
        Long referenceId,
        int quantity
) implements CharacterInventoryItemRequestDto { }