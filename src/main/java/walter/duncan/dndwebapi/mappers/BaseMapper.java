package walter.duncan.dndwebapi.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<TRequestDto, TResponseDto, TModel, TEntity> implements Mapper<TRequestDto, TResponseDto, TModel, TEntity> {
    public List<TModel> toModels(List<TRequestDto> requestDtos) {
        var result = new ArrayList<TModel>();
        if (requestDtos == null) return result;

        for (TRequestDto requestDto : requestDtos) {
            result.add(this.toModel(requestDto));
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

    public List<TModel> fromEntities(List<TEntity> entities) {
        var result = new ArrayList<TModel>();
        if (entities == null) return result;

        for (TEntity entity : entities) {
            result.add(this.fromEntity(entity));
        }

        return result;
    }

    public List<TResponseDto> toResponseDtos(List<TModel> models) {
        var result = new ArrayList<TResponseDto>();
        if (models == null) return result;

        for (TModel model : models) {
            result.add(this.toResponseDto(model));
        }

        return result;
    }
}