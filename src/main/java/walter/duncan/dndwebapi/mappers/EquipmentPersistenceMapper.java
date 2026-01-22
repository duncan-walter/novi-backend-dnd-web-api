package walter.duncan.dndwebapi.mappers;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.EquipmentModel;
import walter.duncan.dndwebapi.entities.EquipmentEntity;

@Component
public final class EquipmentPersistenceMapper extends BasePersistenceMapper<EquipmentModel, EquipmentEntity> {
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