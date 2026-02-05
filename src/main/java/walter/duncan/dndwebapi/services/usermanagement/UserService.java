package walter.duncan.dndwebapi.services.usermanagement;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.entities.usermanagement.UserEntity;
import walter.duncan.dndwebapi.repositories.usermanagement.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Entity return type for simplicity's sake
    public UserEntity resolveUser(Jwt jwt) {
        var subjectId = jwt.getSubject();
        var entity = this.userRepository.findByIdentityProviderId(subjectId);

        if (entity.isPresent()) {
            return entity.get();
        }

        var newEntity = new UserEntity();
        newEntity.setIdentityProviderId(subjectId);
        newEntity.setName(jwt.getClaimAsString("name"));
        newEntity.setEmail(jwt.getClaimAsString("email"));

        return this.userRepository.save(newEntity);
    }
}