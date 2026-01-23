package walter.duncan.dndwebapi.services;

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