package walter.duncan.dndwebapi.businessmodels;

public final class EquipmentModel extends GameInformationModel {
    public EquipmentModel(Long id, String name, String description, int valueInCopperPieces, double weightInLbs) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }
}