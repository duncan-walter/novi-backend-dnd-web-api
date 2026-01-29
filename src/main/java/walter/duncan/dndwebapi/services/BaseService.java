package walter.duncan.dndwebapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import walter.duncan.dndwebapi.entities.BaseEntity;
import walter.duncan.dndwebapi.exceptions.ResourceNotFoundException;

public abstract class BaseService<TEntity extends BaseEntity, @NotNull TId, TRepository extends JpaRepository<@NonNull TEntity, @NonNull TId>> {
    protected final TRepository repository;
    private final Class<TEntity> entityClass;

    protected BaseService(TRepository repository, Class<TEntity> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    protected List<TEntity> findByIdsOrThrow(@NonNull List<TId> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }

        var entities = this.repository.findAllById(ids);

        // Note: this is safe in this context since TId is the same as TEntity.getId()
        @SuppressWarnings("unchecked")
        Set<TId> foundIds = entities.stream()
                .map(entity -> (TId) entity.getId())
                .collect(Collectors.toSet());

        List<TId> missingIds = ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        if (!missingIds.isEmpty()) {
            throw this.getResourceNotFoundException(missingIds.getFirst());
        }

        return entities;
    }

    protected void existsByIdOrThrow(@NonNull TId id) {
        if (!this.repository.existsById(id)) {
            throw this.getResourceNotFoundException(id);
        }
    }

    protected TEntity findByIdOrThrow(@NonNull TId id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> this.getResourceNotFoundException(id));
    }

    protected ResourceNotFoundException getResourceNotFoundException(@NonNull TId id) {
        var entityName = entityClass.getSimpleName().replaceAll("Entity", "");
        return new ResourceNotFoundException(entityName + " with id " + id + " not found.");
    }
}