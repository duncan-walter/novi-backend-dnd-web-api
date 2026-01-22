package walter.duncan.dndwebapi.businessmodels;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class WeaponModel extends GameInformationModel {
    private String damageDice;
    private String damageType;
    private int rangeNormal;
    private int rangeLong;
    private boolean isTwoHanded;

    private WeaponModel(
            Long id,
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }

    private WeaponModel(
            Long id,
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs,
            String damageDice,
            String damageType,
            int rangeNormal,
            int rangeLong,
            boolean isTwoHanded
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.rangeNormal = rangeNormal;
        this.rangeLong = rangeLong;
        this.isTwoHanded = isTwoHanded;
    }

    public static WeaponModel create(
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs,
            String damageDice,
            String damageType,
            int rangeNormal,
            int rangeLong,
            boolean isTwoHanded
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
            int valueInCopperPieces,
            double weightInLbs,
            String damageDice,
            String damageType,
            int rangeNormal,
            int rangeLong,
            boolean isTwoHanded
    ) {
        return new WeaponModel(id, name, description, valueInCopperPieces, weightInLbs, damageDice, damageType, rangeNormal, rangeLong, isTwoHanded);
    }

    // Getters
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

    // Setters with validation
    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void setRangeNormal(int rangeNormal) {
        if (rangeNormal > this.rangeLong && this.rangeLong != 0) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.WEAPON_NORMAL_RANGE_EXCEEDS_LONG_RANGE,
                    "Weapon normal range cannot exceed long range."
            );
        }

        this.rangeNormal = rangeNormal;
    }

    public void setRangeLong(int rangeLong) {
        if (rangeLong < this.rangeNormal && this.rangeNormal != 0) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.WEAPON_LONG_RANGE_LESS_THAN_NORMAL_RANGE,
                    "Weapon long range cannot be less than normal range."
            );
        }

        this.rangeLong = rangeLong;
    }

    public void setIsTwoHanded(boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
    }
}