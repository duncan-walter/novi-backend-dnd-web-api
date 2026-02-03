package walter.duncan.dndwebapi.mappers;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BasePersistenceMapper<
        TModel,
        TEntity,
        TModelCollection extends Collection<TModel>,
        TEntityCollection extends Collection<TEntity>
> implements PersistenceMapper<TModel, TEntity, TModelCollection, TEntityCollection> {
    // Default implementation instantiates a List. Override if another collection instantiation (like Set) is needed.
    @SuppressWarnings("unchecked")
    protected TModelCollection createModelCollection() {
        return (TModelCollection) new ArrayList<TModel>();
    }

    // Default implementation instantiates a List. Override if another collection instantiation (like Set) is needed.
    @SuppressWarnings("unchecked")
    protected TEntityCollection createEntityCollection() {
        return (TEntityCollection) new ArrayList<TEntity>();
    }

    @Override
    public TModelCollection toModels(Collection<TEntity> entities) {
        var result = this.createModelCollection();
        if (entities == null) return result;

        for (TEntity entity : entities) {
            result.add(this.toModel(entity));
        }

        return result;
    }

    @Override
    public TEntityCollection toEntities(Collection<TModel> models) {
        var result = this.createEntityCollection();
        if (models == null) return result;

        for (TModel model : models) {
            result.add(this.toEntity(model));
        }

        return result;
    }
}