package walter.duncan.dndwebapi.repositories.charactermanagement;

import java.util.List;
import java.util.Optional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.entities.usermanagement.UserEntity;

@Repository
public interface CharacterRepository extends JpaRepository<@NonNull CharacterEntity, @NonNull Long> {
    @Query("""
        SELECT CASE WHEN EXISTS (
            SELECT 1
            FROM CharacterInventoryItemEntity cii
            WHERE cii.referenceId = :referenceId
              AND cii.type = :type
        ) THEN true ELSE false END
        """)
    boolean existsInventoryItemReference(
            @Param("referenceId") Long referenceId,
            @Param("type") CharacterInventoryItemType type
    );

    List<CharacterEntity> findByUser(UserEntity user);
    Optional<CharacterEntity> findByIdAndUser(Long id, UserEntity user);
    boolean existsByIdAndUser(Long id, UserEntity user);
}