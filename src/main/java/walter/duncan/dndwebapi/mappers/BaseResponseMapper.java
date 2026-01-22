package walter.duncan.dndwebapi.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseResponseMapper<TResponseDto, TModel> implements ResponseMapper<TResponseDto, TModel> {
    public List<TResponseDto> toDtos(List<TModel> models) {
        var result = new ArrayList<TResponseDto>();
        if (models == null) return result;

        for (TModel model : models) {
            result.add(this.toDto(model));
        }

        return result;
    }
}