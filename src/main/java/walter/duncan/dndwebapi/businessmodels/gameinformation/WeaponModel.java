package walter.duncan.dndwebapi.businessmodels.gameinformation;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class WeaponModel extends GameInformationModel {
    //region Fields
    private String damageDice;
    private String damageType;
    private Integer rangeNormal;
    private Integer rangeLong;
    private Boolean isTwoHanded;
    //endregion

    //region Constructors
    private WeaponModel(
            Long id,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }

    private WeaponModel(
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
    //endregion

    //region Factory methods
    public static WeaponModel create(
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
        var model = new WeaponModel(null, name, description, valueInCopperPieces, weightInLbs);
        model.setDamageDice(damageDice);
        model.setDamageType(damageType);
        model.setRangeNormal(rangeNormal);
        model.setRangeLong(rangeLong);
        model.setIsTwoHanded(isTwoHanded);

        return model;
    }

    public static WeaponModel restore(
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
        return new WeaponModel(id, name, description, valueInCopperPieces, weightInLbs, damageDice, damageType, rangeNormal, rangeLong, isTwoHanded);
    }
    //endregion

    //region Getters
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
    //endregion

    //region Setters
    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void setRangeNormal(Integer rangeNormal) {
        if (this.rangeLong != null && rangeNormal > this.rangeLong) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.WEAPON_NORMAL_RANGE_EXCEEDS_LONG_RANGE,
                    "Weapon normal range cannot exceed long range."
            );
        }

        this.rangeNormal = rangeNormal;
    }

    public void setRangeLong(Integer rangeLong) {
        if (this.rangeNormal != null && rangeLong < this.rangeNormal) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.WEAPON_LONG_RANGE_LESS_THAN_NORMAL_RANGE,
                    "Weapon long range cannot be less than normal range."
            );
        }

        this.rangeLong = rangeLong;
    }

    public void setIsTwoHanded(Boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
    }
    //endregion
}