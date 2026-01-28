package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record EquipmentCharacterInventoryItemResponseDto(
        Long id,
        Long referenceId,
        String type,
        String description,
        Long valueInGoldPieces,
        Double weightInLbs,
        int quantity
) { }