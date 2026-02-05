package walter.duncan.dndwebapi.repositories.encountermanagement;

import java.util.Collection;
import java.util.Optional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterState;
import walter.duncan.dndwebapi.entities.usermanagement.UserEntity;

@Repository
public interface EncounterRepository extends JpaRepository<@NonNull EncounterEntity, @NonNull Long> {
    @Query("""
        SELECT COUNT(e) > 0
        FROM EncounterEntity e
        JOIN e.participants p
        WHERE p.character.id = :characterId
        AND e.state IN :states
        """)
    boolean existsByParticipantAndState(
            @Param("characterId") Long characterId,
            @Param("states") Collection<EncounterState> states
    );

    Optional<EncounterEntity> findByIdAndUser(Long id, UserEntity user);
}