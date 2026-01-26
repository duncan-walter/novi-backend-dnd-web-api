package walter.duncan.dndwebapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return this.id;
    }

    /**
     * Exists to support reattaching existing sub-entities due to mapping restrictions in the
     * entity > business model > entity mapping flow where persistence identity is lost.
     * Using this outside persistence mapping is a developer error. Beware.
     */
    public void setId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("We found a developer that cannot read!");
        }

        this.id = id;
    }
}