package walter.duncan.dndwebapi.repositories.encountermanagement;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;

@Repository
interface EncounterRepository extends JpaRepository<@NonNull EncounterEntity, @NonNull Long> { }