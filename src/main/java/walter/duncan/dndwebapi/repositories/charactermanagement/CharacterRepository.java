package walter.duncan.dndwebapi.repositories.charactermanagement;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<@NonNull CharacterEntity, @NonNull Long> { }