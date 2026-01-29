package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public final class EquipmentCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    private final Long referenceId;

    public EquipmentCharacterInventoryItemResponseDto(
            Long id,
            Long referenceId,
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        super(id, type, name, description, valueInCopperPieces, weightInLbs, quantity);
        this.referenceId = referenceId;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }
}