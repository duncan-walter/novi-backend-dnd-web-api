package walter.duncan.dndwebapi.mappers.gameinformation.weapon;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.entities.gameinformation.WeaponEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public final class WeaponPersistenceMapper
        extends BasePersistenceMapper<WeaponModel, WeaponEntity, List<WeaponModel>, List<WeaponEntity>> {
    @Override
    public WeaponModel toModel(WeaponEntity entity) {
        return WeaponModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getValueInCopperPieces(),
                entity.getWeightInLbs(),
                entity.getDamageDice(),
                entity.getDamageType(),
                entity.getRangeNormal(),
                entity.getRangeLong(),
                entity.getIsTwoHanded()
        );
    }

    @Override
    public WeaponEntity toEntity(WeaponModel model) {
        var entity = new WeaponEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(WeaponModel model, WeaponEntity entity) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setValueInCopperPieces(model.getValueInCopperPieces());
        entity.setWeightInLbs(model.getWeightInLbs());
        entity.setDamageDice(model.getDamageDice());
        entity.setDamageType(model.getDamageType());
        entity.setRangeNormal(model.getRangeNormal());
        entity.setRangeLong(model.getRangeLong());
        entity.setIsTwoHanded(model.getIsTwoHanded());
    }
}