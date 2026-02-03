package walter.duncan.dndwebapi.mappers.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterTypeModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterTypeEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public final class CharacterTypePersistenceMapper
        extends BasePersistenceMapper<CharacterTypeModel, CharacterTypeEntity, List<CharacterTypeModel>, List<CharacterTypeEntity>> {
    @Override
    public CharacterTypeModel toModel(CharacterTypeEntity entity) {
        return CharacterTypeModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getColor()
        );
    }

    @Override
    public CharacterTypeEntity toEntity(CharacterTypeModel model) {
        var entity = new CharacterTypeEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(CharacterTypeModel model, CharacterTypeEntity entity) {
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setColor(model.getColor());
    }
}