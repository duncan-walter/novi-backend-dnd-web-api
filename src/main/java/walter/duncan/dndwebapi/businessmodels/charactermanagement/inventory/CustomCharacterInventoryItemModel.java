package walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory;

public final class CustomCharacterInventoryItemModel extends CharacterInventoryItemModel {
    public CustomCharacterInventoryItemModel(
            Long id,
            Long referenceId,
            CharacterInventoryItemType type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        super(id, referenceId, type, name, description, valueInCopperPieces, weightInLbs, quantity);
    }
}