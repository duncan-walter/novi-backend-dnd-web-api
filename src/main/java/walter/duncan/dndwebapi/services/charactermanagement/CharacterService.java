package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterAlignment;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class CharacterService extends BaseService<CharacterEntity, Long, CharacterRepository> {
    private final CharacterPersistenceMapper mapper;
    private final CharacterTypeService characterTypeService;
    private final CharacterRaceService characterRaceService;
    private final CharacterClassService characterClassService;

    protected CharacterService(
            CharacterRepository repository,
            CharacterPersistenceMapper mapper,
            CharacterTypeService characterTypeService,
            CharacterRaceService characterRaceService,
            CharacterClassService characterClassService
    ) {
        super(repository, CharacterEntity.class);
        this.mapper = mapper;
        this.characterTypeService = characterTypeService;
        this.characterRaceService = characterRaceService;
        this.characterClassService = characterClassService;
    }

    public List<CharacterModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public CharacterModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    public CharacterModel create(CharacterRequestDto requestDto) {
        var model = this.createCharacterModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    public CharacterModel update(Long id, CharacterRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.createCharacterModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    public void deleteById(Long id) {
        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    private CharacterModel createCharacterModel(CharacterRequestDto requestDto) {
        return CharacterModel.create(
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
    }
}