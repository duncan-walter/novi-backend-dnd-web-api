package walter.duncan.dndwebapi.services;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.EquipmentModel;
import walter.duncan.dndwebapi.dtos.EquipmentRequestDto;
import walter.duncan.dndwebapi.entities.EquipmentEntity;
import walter.duncan.dndwebapi.mappers.EquipmentPersistenceMapper;
import walter.duncan.dndwebapi.repositories.EquipmentRepository;

@Service
public class EquipmentService extends BaseService<EquipmentEntity, Long, EquipmentRepository> {
    private final EquipmentPersistenceMapper mapper;

    protected EquipmentService(EquipmentRepository repository, EquipmentPersistenceMapper mapper) {
        super(repository, EquipmentEntity.class);
        this.mapper = mapper;
    }

    public List<EquipmentModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public EquipmentModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    public EquipmentModel create(EquipmentRequestDto requestDto) {
        var model = this.requestDtoToModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    public EquipmentModel update(Long id, EquipmentRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.requestDtoToModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    public void deleteById(Long id) {
        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    private EquipmentModel requestDtoToModel(EquipmentRequestDto requestDto) {
        return EquipmentModel.create(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getValueInCopperPieces(),
                requestDto.getWeightInLbs()
        );
    }
}