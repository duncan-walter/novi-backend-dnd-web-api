package walter.duncan.dndwebapi.services.charactermanagement.factories;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterAlignment;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterClassService;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterRaceService;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterTypeService;

@Component
public class CharacterFactory {
    private final CharacterPersistenceMapper mapper;
    private final CharacterInventoryResolver inventoryResolver;
    private final CharacterTypeService characterTypeService;
    private final CharacterRaceService characterRaceService;
    private final CharacterClassService characterClassService;

    public CharacterFactory(
            CharacterPersistenceMapper mapper,
            CharacterInventoryResolver inventoryResolver,
            CharacterTypeService characterTypeService,
            CharacterRaceService characterRaceService,
            CharacterClassService characterClassService
    ) {
        this.mapper = mapper;
        this.inventoryResolver = inventoryResolver;
        this.characterTypeService = characterTypeService;
        this.characterRaceService = characterRaceService;
        this.characterClassService = characterClassService;
    }

    public CharacterModel create(CharacterEntity entity) {
        var model = this.mapper.toModel(entity);
        var inventory = this.inventoryResolver.resolveEntity(entity.getInventory());
        model.setInventory(inventory);

        return model;
    }

    public CharacterModel create(CharacterRequestDto requestDto) {
        var model = CharacterModel.create(
                requestDto.name(),
                requestDto.charisma(),
                requestDto.constitution(),
                requestDto.dexterity(),
                requestDto.intelligence(),
                requestDto.strength(),
                requestDto.wisdom(),
                requestDto.maxHitPoints(),
                requestDto.currentHitPoints(),
                requestDto.experiencePoints(),
                requestDto.size(),
                requestDto.copperPieces(),
                requestDto.silverPieces(),
                requestDto.electrumPieces(),
                requestDto.goldPieces(),
                requestDto.platinumPieces(),
                requestDto.notes(),
                CharacterAlignment.fromCode(requestDto.alignmentCode()),
                this.characterTypeService.findById(requestDto.typeId()),
                this.characterRaceService.findById(requestDto.raceId()),
                this.characterClassService.findById(requestDto.classId())
        );

        if (requestDto.inventory() != null) {
            var inventory = this.inventoryResolver.resolveRequest(requestDto.inventory());
            model.setInventory(inventory);
        }

        return model;
    }

    public CharacterModel create(Long id, CharacterRequestDto requestDto) {
        var model = this.create(requestDto);
        model.setId(id);

        return model;
    }
}