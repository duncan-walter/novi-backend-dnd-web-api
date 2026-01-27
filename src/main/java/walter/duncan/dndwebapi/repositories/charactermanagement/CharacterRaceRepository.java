package walter.duncan.dndwebapi.repositories.charactermanagement;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.charactermanagement.CharacterRaceEntity;

@Repository
public interface CharacterRaceRepository extends JpaRepository<@NonNull CharacterRaceEntity, @NonNull Long> { }