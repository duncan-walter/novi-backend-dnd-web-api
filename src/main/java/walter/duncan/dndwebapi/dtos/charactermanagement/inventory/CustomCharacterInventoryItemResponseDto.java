package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public final class CustomCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    public CustomCharacterInventoryItemResponseDto(
            Long id,
            Long referenceId,
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        super(id, referenceId, type, name, description, valueInCopperPieces, weightInLbs, quantity);
    }
}