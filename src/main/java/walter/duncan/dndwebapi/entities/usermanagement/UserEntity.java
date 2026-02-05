package walter.duncan.dndwebapi.entities.usermanagement;

import jakarta.persistence.*;
import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "identity_provider_id", unique = true, nullable = false)
    private String identityProviderId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    //region Getters & Setters
    public String getIdentityProviderId() {
        return this.identityProviderId;
    }

    public void setIdentityProviderId(String identityProviderId) {
        this.identityProviderId = identityProviderId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion
}