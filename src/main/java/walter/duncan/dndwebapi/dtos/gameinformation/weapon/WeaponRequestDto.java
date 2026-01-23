package walter.duncan.dndwebapi.dtos.gameinformation.weapon;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import walter.duncan.dndwebapi.dtos.gameinformation.GameInformationRequestDto;

public final class WeaponRequestDto extends GameInformationRequestDto {
    @Size(max = 50)
    private String damageDice;

    @Size(max = 50)
    private String damageType;

    @PositiveOrZero
    private Integer rangeNormal;

    @PositiveOrZero
    private Integer rangeLong;

    private Boolean isTwoHanded;

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