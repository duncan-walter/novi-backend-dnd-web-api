package walter.duncan.dndwebapi.services.encountermanagement;

import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class EncounterService extends BaseService<EncounterEntity, Long, EncounterRepository> {
    private final EncounterPersistenceMapper mapper;

    protected EncounterService(
            EncounterRepository repository,
            EncounterPersistenceMapper mapper
    ) {
        super(repository, EncounterEntity.class);
        this.mapper = mapper;
    }

    public Set<EncounterModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public EncounterModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    @Transactional
    public EncounterModel create(EncounterRequestDto requestDto) {
        var model = EncounterModel.create(requestDto.title(), requestDto.description());
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }
}