package walter.duncan.dndwebapi.dtos.gameinformation.weapon;

import walter.duncan.dndwebapi.dtos.gameinformation.GameInformationResponseDto;

public final class WeaponResponseDto extends GameInformationResponseDto {
    private final String damageDice;
    private final String damageType;
    private final Integer rangeNormal;
    private final Integer rangeLong;
    private final Boolean isTwoHanded;

    public WeaponResponseDto(
            Long id,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            String damageDice,
            String damageType,
            Integer rangeNormal,
            Integer rangeLong,
            Boolean isTwoHanded
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
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
}