package walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory;

import java.util.ArrayList;
import java.util.List;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public enum CharacterInventoryItemType {
    WEAPON("weapon"),
    EQUIPMENT("equipment"),
    CUSTOM("custom");

    private final String typeString;

    CharacterInventoryItemType(String typeString) {
        this.typeString = typeString;
    }

    public static CharacterInventoryItemType fromTypeString(String typeString) {
        for (CharacterInventoryItemType type : values()) {
            if (type.getTypeString().equals(typeString)) {
                return type;
            }
        }

        List<String> validCharacterInventoryItemTypes = new ArrayList<>();
        for (CharacterInventoryItemType type : values()) {
            validCharacterInventoryItemTypes.add(type.getTypeString());
        }

        throw new BusinessRuleViolationException(
                BusinessRuleViolation.CHARACTER_INVENTORY_ITEM_TYPE_NOT_SUPPORTED,
                String.format(
                        "One of the provided inventory item types is not supported. Use one of the following inventory item types: '%s'.",
                        String.join(", ", validCharacterInventoryItemTypes)
                )
        );
    }

    public String getTypeString() {
        return this.typeString;
    }
}