package walter.duncan.dndwebapi.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePersistenceMapper<TModel, TEntity> implements PersistenceMapper<TModel, TEntity> {
    public List<TModel> toModels(List<TEntity> entities) {
        var result = new ArrayList<TModel>();
        if (entities == null) return result;

        for (TEntity entity : entities) {
            result.add(this.toModel(entity));
        }

        return result;
    }

    public List<TEntity> toEntities(List<TModel> models) {
        var result = new ArrayList<TEntity>();
        if (models == null) return result;

        for (TModel model : models) {
            result.add(this.toEntity(model));
        }

        return result;
    }
}