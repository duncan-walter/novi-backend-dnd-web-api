package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterRaceModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterRaceEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterRacePersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRaceRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class CharacterRaceService extends BaseService<CharacterRaceEntity, Long, CharacterRaceRepository> {
    private final CharacterRacePersistenceMapper mapper;

    protected CharacterRaceService(CharacterRaceRepository repository, CharacterRacePersistenceMapper mapper) {
        super(repository, CharacterRaceEntity.class);
        this.mapper = mapper;
    }

    public List<CharacterRaceModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public CharacterRaceModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }
}