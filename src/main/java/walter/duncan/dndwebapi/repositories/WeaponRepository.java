package walter.duncan.dndwebapi.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.WeaponEntity;

@Repository
public interface WeaponRepository extends JpaRepository<@NonNull WeaponEntity, @NonNull Long> { }