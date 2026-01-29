package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record CustomCharacterInventoryItemRequestDto(
        String type,
        String name,
        String description,
        Long valueInCopperPieces,
        Double weightInLbs,
        int quantity
) implements CharacterInventoryItemRequestDto { }