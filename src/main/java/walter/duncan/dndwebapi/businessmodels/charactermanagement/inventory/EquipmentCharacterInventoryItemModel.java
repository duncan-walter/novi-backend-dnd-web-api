package walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory;

import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;

public final class EquipmentCharacterInventoryItemModel extends CharacterInventoryItemModel {
    private final EquipmentModel equipment;

    public EquipmentCharacterInventoryItemModel(
            Long id,
            EquipmentModel equipment,
            int quantity
    ) {
        super(
                id, equipment.getId(),
                CharacterInventoryItemType.EQUIPMENT,
                equipment.getName(),
                equipment.getDescription(),
                equipment.getValueInCopperPieces(),
                equipment.getWeightInLbs(),
                quantity
        );
        this.equipment = equipment;
    }

    public EquipmentModel getEquipment() {
        return this.equipment;
    }
}