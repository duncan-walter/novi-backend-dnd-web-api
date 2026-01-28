package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record CharacterInventoryItemRequestDto(
        Long referenceId,
        String type,
        int quantity
) { }