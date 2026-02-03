package walter.duncan.dndwebapi.mappers.charactermanagement.inventory;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.CharacterInventoryItemModel;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.WeaponCharacterInventoryItemModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.CharacterInventoryItemResponseDto;
import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.CustomCharacterInventoryItemResponseDto;
import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.EquipmentCharacterInventoryItemResponseDto;
import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.WeaponCharacterInventoryItemResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public class CharacterInventoryItemResponseMapper
        extends BaseResponseMapper<CharacterInventoryItemResponseDto, CharacterInventoryItemModel, List<CharacterInventoryItemResponseDto>> {
    @Override
    public CharacterInventoryItemResponseDto toDto(CharacterInventoryItemModel model) {
        return switch (model.getType()) {
            case CharacterInventoryItemType.WEAPON -> {
                var weaponModel = ((WeaponCharacterInventoryItemModel) model).getWeapon();
                yield new WeaponCharacterInventoryItemResponseDto(
                        model.getReferenceId(),
                        model.getType().getTypeString(),
                        model.getName(),
                        model.getDescription(),
                        model.getValueInCopperPieces(),
                        model.getWeightInLbs(),
                        model.getQuantity(),
                        weaponModel.getDamageDice(),
                        weaponModel.getDamageType(),
                        weaponModel.getRangeNormal(),
                        weaponModel.getRangeLong(),
                        weaponModel.getIsTwoHanded()
                );
            }
            case CharacterInventoryItemType.EQUIPMENT -> new EquipmentCharacterInventoryItemResponseDto(
                    model.getReferenceId(),
                    model.getType().getTypeString(),
                    model.getName(),
                    model.getDescription(),
                    model.getValueInCopperPieces(),
                    model.getWeightInLbs(),
                    model.getQuantity()
                );
            case CharacterInventoryItemType.CUSTOM -> new CustomCharacterInventoryItemResponseDto(
                    model.getType().getTypeString(),
                    model.getName(),
                    model.getDescription(),
                    model.getValueInCopperPieces(),
                    model.getWeightInLbs(),
                    model.getQuantity()
            );
        };
    }
}