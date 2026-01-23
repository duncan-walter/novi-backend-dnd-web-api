package walter.duncan.dndwebapi.businessmodels.gameinformation;

public final class EquipmentModel extends GameInformationModel {
    private EquipmentModel(
            Long id,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs
    ) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }

    public static EquipmentModel create(
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs
    ) {
        return new EquipmentModel(null, name, description, valueInCopperPieces, weightInLbs);
    }

    public static EquipmentModel restore(
            Long id,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs
    ) {
        return new EquipmentModel(id, name, description, valueInCopperPieces, weightInLbs);
    }
}