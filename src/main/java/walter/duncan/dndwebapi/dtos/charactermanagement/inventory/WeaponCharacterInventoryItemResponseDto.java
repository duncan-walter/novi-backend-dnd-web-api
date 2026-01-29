package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Looks like duplication compared to Custom and Equipment but cannot be handled in a polymorphic way.
@JsonPropertyOrder({"type", "name", "description", "valueInCopperPieces", "weightInLbs", "damageDice", "damageType", "rangeNormal", "rangeLong", "isTwoHanded", "quantity", "referenceId"})
public final class WeaponCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    private final Long referenceId;
    private final String damageDice;
    private final String damageType;
    private final Integer rangeNormal;
    private final Integer rangeLong;
    private final Boolean isTwoHanded;

    public WeaponCharacterInventoryItemResponseDto(
            Long referenceId,
            String type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity,
            String damageDice,
            String damageType,
            Integer rangeNormal,
            Integer rangeLong,
            Boolean isTwoHanded
    ) {
        super(type, name, description, valueInCopperPieces, weightInLbs, quantity);
        this.referenceId = referenceId;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.rangeNormal = rangeNormal;
        this.rangeLong = rangeLong;
        this.isTwoHanded = isTwoHanded;
    }

    public String getDamageDice() {
        return this.damageDice;
    }

    public String getDamageType() {
        return this.damageType;
    }

    public Integer getRangeNormal() {
        return this.rangeNormal;
    }

    public Integer getRangeLong() {
        return this.rangeLong;
    }

    public Boolean getIsTwoHanded() {
        return this.isTwoHanded;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }
}