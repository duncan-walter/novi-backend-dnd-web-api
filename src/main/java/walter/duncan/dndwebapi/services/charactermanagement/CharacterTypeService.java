package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterTypeModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterTypeEntity;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterTypePersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterTypeRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class CharacterTypeService extends BaseService<CharacterTypeEntity, Long, CharacterTypeRepository> {
    private final CharacterTypePersistenceMapper mapper;

    protected CharacterTypeService(CharacterTypeRepository repository, CharacterTypePersistenceMapper mapper) {
        super(repository, CharacterTypeEntity.class);
        this.mapper = mapper;
    }

    public List<CharacterTypeModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public CharacterTypeModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }
}