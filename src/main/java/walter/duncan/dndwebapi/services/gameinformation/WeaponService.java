package walter.duncan.dndwebapi.services.gameinformation;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.entities.gameinformation.WeaponEntity;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
import walter.duncan.dndwebapi.mappers.gameinformation.weapon.WeaponPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.repositories.gameinformation.WeaponRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class WeaponService extends BaseService<WeaponEntity, Long, WeaponRepository> {
    private final WeaponPersistenceMapper mapper;
    private final CharacterRepository characterRepository;

    protected WeaponService(
            WeaponRepository repository,
            WeaponPersistenceMapper mapper,
            CharacterRepository characterRepository
    ) {
        super(repository, WeaponEntity.class);
        this.mapper = mapper;
        this.characterRepository = characterRepository;
    }

    public List<WeaponModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public WeaponModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    public List<WeaponModel> findByIds(List<Long> ids) {
        return this.mapper.toModels(this.findByIdsOrThrow(ids));
    }

    @Transactional
    public WeaponModel create(WeaponRequestDto requestDto) {
        var model = this.createWeaponModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    @Transactional
    public WeaponModel update(Long id, WeaponRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.createWeaponModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    @Transactional
    public void deleteById(Long id) {
        if (this.characterRepository.existsInventoryItemReference(id, CharacterInventoryItemType.WEAPON)) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.RESOURCE_MAY_NOT_BE_DELETED_WHEN_REFERENCED_IN_INVENTORY,
                    String.format("Weapon with id %s may not be deleted since it is referenced in a character's inventory.", id)
            );
        }

        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    private WeaponModel createWeaponModel(WeaponRequestDto requestDto) {
        return WeaponModel.create(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getValueInCopperPieces(),
                requestDto.getWeightInLbs(),
                requestDto.getDamageDice(),
                requestDto.getDamageType(),
                requestDto.getRangeNormal(),
                requestDto.getRangeLong(),
                requestDto.getIsTwoHanded()
        );
    }
}