package walter.duncan.dndwebapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "weapons")
public final class WeaponEntity extends GameInformationEntity {
    @Column(name = "damage_dice", length = 50)
    private String damageDice;

    @Column(name = "damage_type", length = 50)
    private String damageType;

    @Column(name = "range_normal")
    @Min(0)
    private int rangeNormal;

    @Column(name = "range_long")
    @Min(0)
    private int rangeLong;

    @Column(name = "is_two_handed")
    private boolean isTwoHanded = false;

    // Getters and setters
    public String getDamageDice() {
        return this.damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public String getDamageType() {
        return this.damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public int getRangeNormal() {
        return this.rangeNormal;
    }

    public void setRangeNormal(int rangeNormal) {
        this.rangeNormal = rangeNormal;
    }

    public int getRangeLong() {
        return this.rangeLong;
    }

    public void setRangeLong(int rangeLong) {
        this.rangeLong = rangeLong;
    }

    public boolean getIsTwoHanded() {
        return this.isTwoHanded;
    }

    public void setIsTwoHanded(boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
    }
}