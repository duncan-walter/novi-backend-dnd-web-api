package walter.duncan.dndwebapi.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public final class WeaponRequestDto extends GameInformationRequestDto {
    @Size(max = 50)
    private String damageDice;

    @Size(max = 50)
    private String damageType;

    @Positive
    private int rangeNormal;

    @Positive
    private int rangeLong;

    private boolean isTwoHanded;

    public String getDamageDice() {
        return this.damageDice;
    }

    public String getDamageType() {
        return this.damageType;
    }

    public int getRangeNormal() {
        return this.rangeNormal;
    }

    public int getRangeLong() {
        return this.rangeLong;
    }
}