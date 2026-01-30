package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.*;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "character_portraits")
public final class CharacterPortraitEntity extends BaseEntity {
    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "stored_file_name", nullable = false)
    private String storedFileName;

    @Column(name = "mime_type")
    private String mimeType;

    @OneToOne
    @JoinColumn(name = "character_id", unique = true, nullable = false)
    private CharacterEntity character;
}