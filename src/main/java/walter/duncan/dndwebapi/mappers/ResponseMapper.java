package walter.duncan.dndwebapi.mappers;

import java.util.List;

public interface ResponseMapper<TResponseDto, TModel> {
    TResponseDto toDto(TModel model);
    List<TResponseDto> toDtos(List<TModel> models);
}