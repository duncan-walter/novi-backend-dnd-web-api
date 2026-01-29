package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Even though we define the properties in order in the constructor, Jackson does not parse them that way.
@JsonPropertyOrder({"type", "name", "description", "valueInCopperPieces", "weightInLbs", "quantity"})
public final class CustomCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    public CustomCharacterInventoryItemResponseDto(
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        super(type, name, description, valueInCopperPieces, weightInLbs, quantity);
    }
}