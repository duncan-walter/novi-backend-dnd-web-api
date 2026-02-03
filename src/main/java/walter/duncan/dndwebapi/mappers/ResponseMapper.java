package walter.duncan.dndwebapi.mappers;

import java.util.Collection;

public interface ResponseMapper<
        TResponseDto,
        TModel,
        TCollection extends Collection<TResponseDto>
> {
    TResponseDto toDto(TModel model);
    TCollection toDtos(Collection<TModel> models);
}