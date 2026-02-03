package walter.duncan.dndwebapi.repositories.encountermanagement;

import java.util.List;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestEntity;

@Repository
public interface EncounterJoinRequestRepository extends JpaRepository<@NonNull EncounterJoinRequestEntity, @NonNull Long> {
    List<EncounterJoinRequestEntity> findByEncounterId(Long encounterId);
}