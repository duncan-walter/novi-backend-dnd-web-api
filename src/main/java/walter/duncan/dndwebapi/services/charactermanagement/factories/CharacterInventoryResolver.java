package walter.duncan.dndwebapi.services.charactermanagement.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.CharacterInventoryItemModel;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.EquipmentCharacterInventoryItemModel;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.WeaponCharacterInventoryItemModel;
import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;
import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.CharacterInventoryItemRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.mappers.charactermanagement.inventory.CharacterInventoryItemPersistenceMapper;
import walter.duncan.dndwebapi.services.gameinformation.EquipmentService;
import walter.duncan.dndwebapi.services.gameinformation.WeaponService;

@Component
public class CharacterInventoryResolver {
    private final EquipmentService equipmentService;
    private final WeaponService weaponService;
    private final CharacterInventoryItemPersistenceMapper characterInventoryItemPersistenceMapper;

    protected CharacterInventoryResolver(
            EquipmentService equipmentService,
            WeaponService weaponService,
            CharacterInventoryItemPersistenceMapper characterInventoryItemPersistenceMapper
    ) {
        this.equipmentService = equipmentService;
        this.weaponService = weaponService;
        this.characterInventoryItemPersistenceMapper = characterInventoryItemPersistenceMapper;
    }

    public List<CharacterInventoryItemModel> resolveEntity(List<CharacterInventoryItemEntity> entities) {
        List<Long> weaponIds = new ArrayList<>();
        List<Long> equipmentIds = new ArrayList<>();

        for (CharacterInventoryItemEntity entity : entities) {
            switch (entity.getType()) {
                case CharacterInventoryItemType.WEAPON -> weaponIds.add(entity.getReferenceId());
                case CharacterInventoryItemType.EQUIPMENT -> equipmentIds.add(entity.getReferenceId());
                case CharacterInventoryItemType.CUSTOM -> { /* TODO: implement */ }
            }
        }

        Map<Long, WeaponModel> weaponModelsById = new HashMap<>();
        for (WeaponModel weaponModel : this.weaponService.findByIds(weaponIds)) {
            weaponModelsById.put(weaponModel.getId(), weaponModel);
        }

        Map<Long, EquipmentModel> equipmentModelsById = new HashMap<>();
        for (EquipmentModel equipmentModel : this.equipmentService.findByIds(equipmentIds)) {
            equipmentModelsById.put(equipmentModel.getId(), equipmentModel);
        }

        this.characterInventoryItemPersistenceMapper.setWeaponModelByIdMap(weaponModelsById);
        this.characterInventoryItemPersistenceMapper.setEquipmentModelByIdMap(equipmentModelsById);

        return this.characterInventoryItemPersistenceMapper.toModels(entities);
    }

    // TODO: Add mechanism for resolving an update request when transitioning away from drop > create for inventory items.
    public List<CharacterInventoryItemModel> resolveRequest(List<CharacterInventoryItemRequestDto> requestDtos) {
        List<CharacterInventoryItemModel> characterInventoryItemModels = new ArrayList<>();

        Map<Long, CharacterInventoryItemRequestDto> weaponRequestDtoById = new HashMap<>();
        Map<Long, CharacterInventoryItemRequestDto> equipmentRequestDtoById = new HashMap<>();

        for (CharacterInventoryItemRequestDto requestDto : requestDtos) {
            switch (walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.CharacterInventoryItemType.fromTypeString(requestDto.type())) {
                case WEAPON -> weaponRequestDtoById.put(requestDto.referenceId(), requestDto);
                case EQUIPMENT -> equipmentRequestDtoById.put(requestDto.referenceId(), requestDto);
                case CUSTOM -> { /* TODO: implement */ }
            }
        }

        for (WeaponModel weaponModel : this.weaponService.findByIds(new ArrayList<>(weaponRequestDtoById.keySet()))) {
            characterInventoryItemModels.add(
                    new WeaponCharacterInventoryItemModel(
                            null,
                            weaponModel,
                            weaponRequestDtoById.get(weaponModel.getId()).quantity()
                    )
            );
        }

        for (EquipmentModel equipmentModel : this.equipmentService.findByIds(new ArrayList<>(equipmentRequestDtoById.keySet()))) {
            characterInventoryItemModels.add(
                    new EquipmentCharacterInventoryItemModel(
                            null,
                            equipmentModel,
                            equipmentRequestDtoById.get(equipmentModel.getId()).quantity()
                    )
            );
        }

        return characterInventoryItemModels;
    }
}