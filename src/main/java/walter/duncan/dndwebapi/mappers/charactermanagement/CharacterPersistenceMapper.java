package walter.duncan.dndwebapi.mappers.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.*;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterPortraitEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.inventory.CharacterInventoryItemPersistenceMapper;

@Component
public final class CharacterPersistenceMapper
        extends BasePersistenceMapper<CharacterModel, CharacterEntity, List<CharacterModel>, List<CharacterEntity>> {
    private final CharacterTypePersistenceMapper characterTypePersistenceMapper;
    private final CharacterRacePersistenceMapper characterRacePersistenceMapper;
    private final CharacterClassPersistenceMapper characterClassPersistenceMapper;
    private final CharacterInventoryItemPersistenceMapper characterInventoryItemPersistenceMapper;

    public CharacterPersistenceMapper(
            CharacterTypePersistenceMapper characterTypePersistenceMapper,
            CharacterRacePersistenceMapper characterRacePersistenceMapper,
            CharacterClassPersistenceMapper characterClassPersistenceMapper,
            CharacterInventoryItemPersistenceMapper characterInventoryItemPersistenceMapper
    ) {
        this.characterTypePersistenceMapper = characterTypePersistenceMapper;
        this.characterRacePersistenceMapper = characterRacePersistenceMapper;
        this.characterClassPersistenceMapper = characterClassPersistenceMapper;
        this.characterInventoryItemPersistenceMapper = characterInventoryItemPersistenceMapper;
    }

    @Override
    public CharacterModel toModel(CharacterEntity entity) {
        return CharacterModel.restore(
                entity.getId(),
                entity.getName(),
                entity.getCharisma(),
                entity.getConstitution(),
                entity.getDexterity(),
                entity.getIntelligence(),
                entity.getStrength(),
                entity.getWisdom(),
                entity.getMaxHitPoints(),
                entity.getCurrentHitPoints(),
                entity.getExperiencePoints(),
                entity.getSize(),
                entity.getCopperPieces(),
                entity.getSilverPieces(),
                entity.getElectrumPieces(),
                entity.getGoldPieces(),
                entity.getPlatinumPieces(),
                entity.getNotes(),
                this.mapPortrait(entity.getPortrait()),
                this.mapAlignment(entity.getAlignment()),
                this.characterTypePersistenceMapper.toModel(entity.getType()),
                this.characterRacePersistenceMapper.toModel(entity.getRace()),
                this.characterClassPersistenceMapper.toModel(entity.getCharacterClass()),
                entity.getUser()
        );
    }

    @Override
    public CharacterEntity toEntity(CharacterModel model) {
        var entity = new CharacterEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(CharacterModel model, CharacterEntity entity) {
        this.characterInventoryItemPersistenceMapper.setCharacterEntity(entity);

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setCharisma(model.getCharisma());
        entity.setConstitution(model.getConstitution());
        entity.setDexterity(model.getDexterity());
        entity.setIntelligence(model.getIntelligence());
        entity.setStrength(model.getStrength());
        entity.setWisdom(model.getWisdom());
        entity.setMaxHitPoints(model.getMaxHitPoints());
        entity.setCurrentHitPoints(model.getCurrentHitPoints());
        entity.setExperiencePoints(model.getExperiencePoints());
        entity.setSize(model.getSize());
        entity.setCopperPieces(model.getCopperPieces());
        entity.setSilverPieces(model.getSilverPieces());
        entity.setElectrumPieces(model.getElectrumPieces());
        entity.setGoldPieces(model.getGoldPieces());
        entity.setPlatinumPieces(model.getPlatinumPieces());
        entity.setNotes(model.getNotes());
        entity.setAlignment(this.mapAlignment(model.getAlignment()));
        entity.setType(this.characterTypePersistenceMapper.toEntity(model.getType()));
        entity.setRace(this.characterRacePersistenceMapper.toEntity(model.getRace()));
        entity.setCharacterClass(this.characterClassPersistenceMapper.toEntity(model.getCharacterClass()));
        entity.setInventory(this.characterInventoryItemPersistenceMapper.toEntities(model.getInventory()));
        entity.setUser(model.getUser());
    }

    private CharacterAlignment mapAlignment(walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment persistedAlignment) {
        return switch (persistedAlignment) {
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_GOOD -> CharacterAlignment.LAWFUL_GOOD;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.NEUTRAL_GOOD -> CharacterAlignment.NEUTRAL_GOOD;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_GOOD -> CharacterAlignment.CHAOTIC_GOOD;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_NEUTRAL -> CharacterAlignment.LAWFUL_NEUTRAL;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.TRUE_NEUTRAL -> CharacterAlignment.TRUE_NEUTRAL;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_NEUTRAL -> CharacterAlignment.CHAOTIC_NEUTRAL;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_EVIL -> CharacterAlignment.LAWFUL_EVIL;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.NEUTRAL_EVIL -> CharacterAlignment.NEUTRAL_EVIL;
            case walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_EVIL -> CharacterAlignment.CHAOTIC_EVIL;
        };
    }

    private walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment mapAlignment(CharacterAlignment alignment) {
        return switch (alignment) {
            case CharacterAlignment.LAWFUL_GOOD -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_GOOD;
            case CharacterAlignment.NEUTRAL_GOOD -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.NEUTRAL_GOOD;
            case CharacterAlignment.CHAOTIC_GOOD -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_GOOD;
            case CharacterAlignment.LAWFUL_NEUTRAL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_NEUTRAL;
            case CharacterAlignment.TRUE_NEUTRAL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.TRUE_NEUTRAL;
            case CharacterAlignment.CHAOTIC_NEUTRAL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_NEUTRAL;
            case CharacterAlignment.LAWFUL_EVIL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.LAWFUL_EVIL;
            case CharacterAlignment.NEUTRAL_EVIL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.NEUTRAL_EVIL;
            case CharacterAlignment.CHAOTIC_EVIL -> walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_EVIL;
        };
    }

    private CharacterPortraitModel mapPortrait(CharacterPortraitEntity persistedPortrait) {
        if (persistedPortrait == null) return null;

        return new CharacterPortraitModel(
                persistedPortrait.getOriginalFileName(),
                persistedPortrait.getStoredFileName(),
                persistedPortrait.getMimeType()
        );
    }
}