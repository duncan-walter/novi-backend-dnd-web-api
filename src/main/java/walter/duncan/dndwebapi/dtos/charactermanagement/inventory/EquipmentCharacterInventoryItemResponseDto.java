package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Looks like duplication compared to Custom and Weapon but cannot be handled in a polymorphic way.
@JsonPropertyOrder({"type", "name", "description", "valueInCopperPieces", "weightInLbs", "quantity", "referenceId"})
public final class EquipmentCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    private final Long referenceId;

    public EquipmentCharacterInventoryItemResponseDto(
            Long referenceId,
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        super(type, name, description, valueInCopperPieces, weightInLbs, quantity);
        this.referenceId = referenceId;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }
}