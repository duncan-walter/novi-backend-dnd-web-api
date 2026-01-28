package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

public record WeaponCharacterInventoryItemResponseDto(
        Long id,
        Long referenceId,
        String type,
        String description,
        Long valueInGoldPieces,
        Double weightInLbs,
        int quantity,
        String damageDice,
        String damageType,
        Integer rangeNormal,
        Integer rangeLong,
        Boolean isTwoHanded
) { }