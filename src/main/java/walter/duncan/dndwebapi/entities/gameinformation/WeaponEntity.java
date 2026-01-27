package walter.duncan.dndwebapi.entities.gameinformation;

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
    private Integer rangeNormal;

    @Column(name = "range_long")
    @Min(0)
    private Integer rangeLong;

    @Column(name = "is_two_handed")
    private Boolean isTwoHanded;

    //region Getters & Setters
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

    public Integer getRangeNormal() {
        return this.rangeNormal;
    }

    public void setRangeNormal(Integer rangeNormal) {
        this.rangeNormal = rangeNormal;
    }

    public Integer getRangeLong() {
        return this.rangeLong;
    }

    public void setRangeLong(Integer rangeLong) {
        this.rangeLong = rangeLong;
    }

    public Boolean getIsTwoHanded() {
        return this.isTwoHanded;
    }

    public void setIsTwoHanded(Boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
    }
    //endregion
}