package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WeaponCharacterInventoryItemResponseDto.class, name = "weapon"),
        @JsonSubTypes.Type(value = EquipmentCharacterInventoryItemResponseDto.class, name = "equipment")
})
public abstract class CharacterInventoryItemResponseDto {
    private final Long id;
    private final Long referenceId;
    private final String type;
    private final String name;
    private final String description;
    private final Long valueInCopperPieces;
    private final Double weightInLbs;
    private final int quantity;

    protected CharacterInventoryItemResponseDto(
            Long id,
            Long referenceId,
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        this.id = id;
        this.referenceId = referenceId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.valueInCopperPieces = valueInCopperPieces;
        this.weightInLbs = weightInLbs;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public Double getWeightInLbs() {
        return this.weightInLbs;
    }

    public int getQuantity() {
        return this.quantity;
    }
}