package walter.duncan.dndwebapi.mappers.gameinformation.equipment;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;
import walter.duncan.dndwebapi.entities.gameinformation.EquipmentEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public final class EquipmentPersistenceMapper
        extends BasePersistenceMapper<EquipmentModel, EquipmentEntity, List<EquipmentModel>, List<EquipmentEntity>> {
    @Override
    public EquipmentModel toModel(EquipmentEntity entity) {
        return EquipmentModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getValueInCopperPieces(),
                entity.getWeightInLbs()
        );
    }

    @Override
    public EquipmentEntity toEntity(EquipmentModel model) {
        var entity = new EquipmentEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(EquipmentModel model, EquipmentEntity entity) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setValueInCopperPieces(model.getValueInCopperPieces());
        entity.setWeightInLbs(model.getWeightInLbs());
    }
}