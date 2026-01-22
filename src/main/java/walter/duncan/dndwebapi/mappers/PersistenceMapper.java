package walter.duncan.dndwebapi.mappers;

import java.util.List;

public interface PersistenceMapper<TModel, TEntity> {
    TModel toModel(TEntity entity);
    List<TModel> toModels(List<TEntity> entities);

    TEntity toEntity(TModel model);
    List<TEntity> toEntities(List<TModel> models);

    void updateEntityFromModel(TModel model, TEntity entity);
}