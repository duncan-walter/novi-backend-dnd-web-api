package walter.duncan.dndwebapi.repositories.usermanagement;

import java.util.Optional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import walter.duncan.dndwebapi.entities.usermanagement.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<@NonNull UserEntity, @NonNull Long> {
    Optional<UserEntity> findByIdentityProviderId(String identityProviderId);
}