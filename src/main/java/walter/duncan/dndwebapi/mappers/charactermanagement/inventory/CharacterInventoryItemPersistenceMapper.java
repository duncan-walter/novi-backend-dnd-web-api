package walter.duncan.dndwebapi.mappers.charactermanagement.inventory;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.*;
import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;
import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemCustomInfoEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public class CharacterInventoryItemPersistenceMapper extends BasePersistenceMapper<CharacterInventoryItemModel, CharacterInventoryItemEntity> {
    private Map<Long, WeaponModel> weaponModelById = new HashMap<>();
    private Map<Long, EquipmentModel> equipmentModelById = new HashMap<>();
    private CharacterEntity characterEntity;

    @Override
    public CharacterInventoryItemModel toModel(CharacterInventoryItemEntity entity) {
        return switch (this.mapType(entity.getType())) {
            case CharacterInventoryItemType.WEAPON -> new WeaponCharacterInventoryItemModel(
                    entity.getId(),
                    this.weaponModelById.get(entity.getReferenceId()),
                    entity.getQuantity()
            );
            case CharacterInventoryItemType.EQUIPMENT -> new EquipmentCharacterInventoryItemModel(
                    entity.getId(),
                    this.equipmentModelById.get(entity.getReferenceId()),
                    entity.getQuantity()
            );
            case CharacterInventoryItemType.CUSTOM -> {
                var customInfo = entity.getCustomInfo();
                yield new CustomCharacterInventoryItemModel(
                        entity.getId(),
                        entity.getReferenceId(),
                        CharacterInventoryItemType.CUSTOM,
                        customInfo.getName(),
                        customInfo.getDescription(),
                        customInfo.getValueInCopperPieces(),
                        customInfo.getWeightInLbs(),
                        entity.getQuantity()
                );
            }
        };
    }

    @Override
    public CharacterInventoryItemEntity toEntity(CharacterInventoryItemModel model) {
        var entity = new CharacterInventoryItemEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(CharacterInventoryItemModel model, CharacterInventoryItemEntity entity) {
        entity.setId(model.getId());
        entity.setReferenceId(model.getReferenceId());
        entity.setType(this.mapType(model.getType()));
        entity.setQuantity(model.getQuantity());
        entity.setCharacter(this.characterEntity);

        if (model.getType().equals(CharacterInventoryItemType.CUSTOM)) {
            var customInfo = new CharacterInventoryItemCustomInfoEntity();
            customInfo.setInventoryItem(entity);
            customInfo.setName(model.getName());
            customInfo.setDescription(model.getDescription());
            customInfo.setValueInCopperPieces(model.getValueInCopperPieces());
            customInfo.setWeightInLbs(model.getWeightInLbs());
            entity.setCustomInfo(customInfo);
        }
    }

    private CharacterInventoryItemType mapType(walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType persistedType) {
        return switch(persistedType) {
            case walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.WEAPON -> CharacterInventoryItemType.WEAPON;
            case walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.EQUIPMENT -> CharacterInventoryItemType.EQUIPMENT;
            case walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.CUSTOM -> CharacterInventoryItemType.CUSTOM;
        };
    }

    private walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType mapType(CharacterInventoryItemType type) {
        return switch(type) {
            case CharacterInventoryItemType.WEAPON -> walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.WEAPON;
            case CharacterInventoryItemType.EQUIPMENT -> walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.EQUIPMENT;
            case CharacterInventoryItemType.CUSTOM -> walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType.CUSTOM;
        };
    }

    public void setCharacterEntity(CharacterEntity characterEntity) {
        this.characterEntity = characterEntity;
    }

    public void setWeaponModelByIdMap(Map<Long, WeaponModel> weaponModelById) {
        this.weaponModelById = weaponModelById;
    }

    public void setEquipmentModelByIdMap(Map<Long, EquipmentModel> equipmentModelById) {
        this.equipmentModelById = equipmentModelById;
    }
}