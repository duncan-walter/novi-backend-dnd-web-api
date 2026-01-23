package walter.duncan.dndwebapi.repositories.gameinformation;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.gameinformation.EquipmentEntity;

@Repository
public interface EquipmentRepository extends JpaRepository<@NonNull EquipmentEntity, @NonNull Long> { }