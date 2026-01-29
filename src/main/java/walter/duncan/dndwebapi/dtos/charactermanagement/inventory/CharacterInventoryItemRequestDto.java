package walter.duncan.dndwebapi.dtos.charactermanagement.inventory;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WeaponCharacterInventoryItemRequestDto.class, name = "weapon"),
        @JsonSubTypes.Type(value = EquipmentCharacterInventoryItemRequestDto.class, name = "equipment"),
        @JsonSubTypes.Type(value = CustomCharacterInventoryItemRequestDto.class, name = "custom")
})
public sealed interface CharacterInventoryItemRequestDto permits
        WeaponCharacterInventoryItemRequestDto,
        EquipmentCharacterInventoryItemRequestDto,
        CustomCharacterInventoryItemRequestDto {
}