package walter.duncan.dndwebapi.mappers.charactermanagement;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterRaceModel;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterRaceEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public class CharacterRacePersistenceMapper extends BasePersistenceMapper<CharacterRaceModel, CharacterRaceEntity> {
    @Override
    public CharacterRaceModel toModel(CharacterRaceEntity entity) {
        return CharacterRaceModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getSpeed()
        );
    }

    @Override
    public CharacterRaceEntity toEntity(CharacterRaceModel model) {
        var entity = new CharacterRaceEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(CharacterRaceModel model, CharacterRaceEntity entity) {
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setSpeed(model.getSpeed());
    }
}