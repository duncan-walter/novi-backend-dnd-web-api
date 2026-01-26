package walter.duncan.dndwebapi.mappers.charactermanagement;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterClassModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterClassEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public class CharacterClassPersistenceMapper extends BasePersistenceMapper<CharacterClassModel, CharacterClassEntity> {
    @Override
    public CharacterClassModel toModel(CharacterClassEntity entity) {
        return CharacterClassModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getHitDie()
        );
    }

    @Override
    public CharacterClassEntity toEntity(CharacterClassModel model) {
        var entity = new CharacterClassEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(CharacterClassModel model, CharacterClassEntity entity) {
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setHitDie(model.getHitDie());
    }
}