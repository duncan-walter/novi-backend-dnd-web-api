package walter.duncan.dndwebapi.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.EquipmentEntity;

@Repository
public interface EquipmentRepository extends JpaRepository<@NonNull EquipmentEntity, @NonNull Long> { }