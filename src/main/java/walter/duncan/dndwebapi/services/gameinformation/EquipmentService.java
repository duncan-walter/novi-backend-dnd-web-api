package walter.duncan.dndwebapi.services.gameinformation;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;
import walter.duncan.dndwebapi.dtos.gameinformation.equipment.EquipmentRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.entities.gameinformation.EquipmentEntity;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
import walter.duncan.dndwebapi.mappers.gameinformation.equipment.EquipmentPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.repositories.gameinformation.EquipmentRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class EquipmentService extends BaseService<EquipmentEntity, Long, EquipmentRepository> {
    private final EquipmentPersistenceMapper mapper;
    private final CharacterRepository characterRepository;

    protected EquipmentService(
            EquipmentRepository repository,
            EquipmentPersistenceMapper mapper,
            CharacterRepository characterRepository
    ) {
        super(repository, EquipmentEntity.class);
        this.mapper = mapper;
        this.characterRepository = characterRepository;
    }

    public List<EquipmentModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public EquipmentModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    public List<EquipmentModel> findByIds(List<Long> ids) {
        return this.mapper.toModels(this.findByIdsOrThrow(ids));
    }

    @Transactional
    public EquipmentModel create(EquipmentRequestDto requestDto) {
        var model = this.createEquipmentModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    @Transactional
    public EquipmentModel update(Long id, EquipmentRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.createEquipmentModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    @Transactional
    public void deleteById(Long id) {
        if (this.characterRepository.existsInventoryItemReference(id, CharacterInventoryItemType.EQUIPMENT)) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.RESOURCE_MAY_NOT_BE_DELETED_WHEN_REFERENCED_IN_INVENTORY,
                    String.format("Equipment with id %s may not be deleted since it is referenced in a character's inventory.", id)
            );
        }

        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    private EquipmentModel createEquipmentModel(EquipmentRequestDto requestDto) {
        return EquipmentModel.create(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getValueInCopperPieces(),
                requestDto.getWeightInLbs()
        );
    }
}