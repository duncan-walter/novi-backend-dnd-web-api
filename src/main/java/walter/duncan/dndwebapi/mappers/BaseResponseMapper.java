package walter.duncan.dndwebapi.mappers;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseResponseMapper<
        TResponseDto,
        TModel,
        TCollection extends Collection<TResponseDto>
> implements ResponseMapper<TResponseDto, TModel, TCollection> {
    // Default implementation instantiates a List. Override if another collection instantiation (like Set) is needed.
    @SuppressWarnings("unchecked")
    protected TCollection createCollection() {
        return (TCollection) new ArrayList<TModel>();
    }

    @Override
    public TCollection toDtos(Collection<TModel> models) {
        var result = this.createCollection();
        if (models == null) return result;

        for (TModel model : models) {
            result.add(this.toDto(model));
        }

        return result;
    }
}