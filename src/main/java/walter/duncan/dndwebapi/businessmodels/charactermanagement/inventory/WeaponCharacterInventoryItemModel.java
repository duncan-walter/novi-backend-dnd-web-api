package walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;

public final class WeaponCharacterInventoryItemModel extends CharacterInventoryItemModel {
    private final WeaponModel weapon;

    public WeaponCharacterInventoryItemModel(
            Long id,
            WeaponModel weapon,
            int quantity
    ) {
        super(
                id,
                weapon.getId(),
                CharacterInventoryItemType.WEAPON,
                weapon.getName(),
                weapon.getDescription(),
                weapon.getValueInCopperPieces(),
                weapon.getWeightInLbs(),
                quantity
        );
        this.weapon = weapon;
    }

    public WeaponModel getWeapon() {
        return this.weapon;
    }
}