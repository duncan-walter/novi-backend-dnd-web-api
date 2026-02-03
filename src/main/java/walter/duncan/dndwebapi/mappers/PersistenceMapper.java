package walter.duncan.dndwebapi.mappers;

import java.util.Collection;

public interface PersistenceMapper<
        TModel,
        TEntity,
        TModelCollection extends Collection<TModel>,
        TEntityCollection extends Collection<TEntity>
> {
    TModel toModel(TEntity entity);
    TModelCollection toModels(Collection<TEntity> entities);

    TEntity toEntity(TModel model);
    TEntityCollection toEntities(Collection<TModel> models);

    void updateEntityFromModel(TModel model, TEntity entity);
}