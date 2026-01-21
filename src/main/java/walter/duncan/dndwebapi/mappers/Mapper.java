package walter.duncan.dndwebapi.mappers;

import java.util.List;

public interface Mapper<TRequestDto, TResponseDto, TModel, TEntity> {
    TModel toModel(TRequestDto requestDto);
    List<TModel> toModels(List<TRequestDto> requestDtos);

    TEntity toEntity(TModel model);
    List<TEntity> toEntities(List<TModel> models);

    TModel fromEntity(TEntity entity);
    List<TModel> fromEntities(List<TEntity> entities);

    TResponseDto toResponseDto(TModel model);
    List<TResponseDto> toResponseDtos(List<TModel> models);
}