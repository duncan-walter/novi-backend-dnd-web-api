package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public final class WeaponCharacterInventoryItemResponseDto extends CharacterInventoryItemResponseDto {
    private final String damageDice;
    private final String damageType;
    private final Integer rangeNormal;
    private final Integer rangeLong;
    private final Boolean isTwoHanded;

    public WeaponCharacterInventoryItemResponseDto(
            Long id,
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
        super(id, referenceId, type, name, description, valueInCopperPieces, weightInLbs, quantity);
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

    public Boolean getTwoHanded() {
        return this.isTwoHanded;
    }
}