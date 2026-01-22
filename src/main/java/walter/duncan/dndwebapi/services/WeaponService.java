package walter.duncan.dndwebapi.services;

import java.util.List;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.WeaponModel;
import walter.duncan.dndwebapi.dtos.WeaponRequestDto;
import walter.duncan.dndwebapi.entities.WeaponEntity;
import walter.duncan.dndwebapi.mappers.WeaponPersistenceMapper;
import walter.duncan.dndwebapi.repositories.WeaponRepository;

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
        var model = this.requestDtoToModel(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    public WeaponModel update(Long id, WeaponRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.requestDtoToModel(requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    public void deleteById(Long id) {
        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    private WeaponModel requestDtoToModel(WeaponRequestDto requestDto) {
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