package walter.duncan.dndwebapi.services.gameinformation;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;
import walter.duncan.dndwebapi.entities.gameinformation.WeaponEntity;
import walter.duncan.dndwebapi.mappers.gameinformation.weapon.WeaponPersistenceMapper;
import walter.duncan.dndwebapi.repositories.gameinformation.WeaponRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class WeaponService extends BaseService<WeaponEntity, Long, WeaponRepository> {
    private final WeaponPersistenceMapper mapper;

    protected WeaponService(WeaponRepository repository, WeaponPersistenceMapper mapper) {
        super(repository, WeaponEntity.class);
        this.mapper = mapper;
    }

    public List<WeaponModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public WeaponModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    public WeaponModel create(WeaponRequestDto requestDto) {
        var model = this.createWeaponModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    public WeaponModel update(Long id, WeaponRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.createWeaponModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    public void deleteById(Long id) {
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