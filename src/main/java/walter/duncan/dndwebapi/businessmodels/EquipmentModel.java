package walter.duncan.dndwebapi.businessmodels;

public final class EquipmentModel extends GameInformationModel {
    private EquipmentModel(
            Long id,
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }

    public static EquipmentModel create(
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs
    ) {
        return new EquipmentModel(null, name, description, valueInCopperPieces, weightInLbs);
    }

    public static EquipmentModel restore(
            Long id,
            String name,
            String description,
            int valueInCopperPieces,
            double weightInLbs
    ) {
        return new EquipmentModel(id, name, description, valueInCopperPieces, weightInLbs);
    }
}