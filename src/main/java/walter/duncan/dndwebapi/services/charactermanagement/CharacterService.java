package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.services.BaseService;
import walter.duncan.dndwebapi.services.charactermanagement.factories.CharacterFactory;

@Service
public class CharacterService extends BaseService<CharacterEntity, Long, CharacterRepository> {
    private final CharacterPersistenceMapper mapper;
    private final CharacterFactory characterFactory;

    protected CharacterService(
            CharacterRepository repository,
            CharacterPersistenceMapper mapper,
            CharacterFactory characterFactory
    ) {
        super(repository, CharacterEntity.class);
        this.mapper = mapper;
        this.characterFactory = characterFactory;
    }

    public List<CharacterModel> findAll() {
        return this.repository.findAll().stream().map(this.characterFactory::create).toList();
    }

    public CharacterModel findById(Long id) {
        return this.characterFactory.create(this.findByIdOrThrow(id));
    }

    @Transactional
    public CharacterModel create(CharacterRequestDto requestDto) {
        var model = this.characterFactory.create(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.characterFactory.create(persistedEntity);
    }

    @Transactional
    public CharacterModel update(Long id, CharacterRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.characterFactory.create(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.characterFactory.create(this.repository.save(persistedEntity));
    }

    @Transactional
    public void deleteById(Long id) {
        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }
}