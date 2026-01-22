package walter.duncan.dndwebapi.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public final class WeaponRequestDto extends GameInformationRequestDto {
    @Size(max = 50)
    private String damageDice;

    @Size(max = 50)
    private String damageType;

    @PositiveOrZero
    private int rangeNormal;

    @PositiveOrZero
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

    public boolean getIsTwoHanded() {
        return this.isTwoHanded;
    }
}