package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterClassModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterClassEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterClassPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterClassRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class CharacterClassService extends BaseService<CharacterClassEntity, Long, CharacterClassRepository> {
    private final CharacterClassPersistenceMapper mapper;

    protected CharacterClassService(CharacterClassRepository repository, CharacterClassPersistenceMapper mapper) {
        super(repository, CharacterClassEntity.class);
        this.mapper = mapper;
    }

    public List<CharacterClassModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public CharacterClassModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }
}