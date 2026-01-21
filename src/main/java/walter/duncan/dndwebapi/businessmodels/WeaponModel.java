package walter.duncan.dndwebapi.businessmodels;

public final class WeaponModel extends GameInformationModel {
    private final String damageDice;
    private final String damageType;
    private final int rangeNormal;
    private final int rangeLong;
    private final boolean isTwoHanded;

    public WeaponModel(
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
        super(name, description, valueInCopperPieces, weightInLbs);
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

    public int getRangeNormal() {
        return this.rangeNormal;
    }

    public int getRangeLong() {
        return this.rangeLong;
    }

    public boolean isTwoHanded() {
        return this.isTwoHanded;
    }
}