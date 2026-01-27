package walter.duncan.dndwebapi.dtos.charactermanagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterResponseDto(
        Long id,
        String name,
        int charisma,
        int charismaModifier,
        int constitution,
        int constitutionModifier,
        int dexterity,
        int dexterityModifier,
        int intelligence,
        int intelligenceModifier,
        int strength,
        int strengthModifier,
        int wisdom,
        int wisdomModifier,
        int maxHitPoints,
        int currentHitPoints,
        int experiencePoints,
        int level,
        String size,
        Integer copperPieces,
        Integer silverPieces,
        Integer electrumPieces,
        Integer goldPieces,
        Integer platinumPieces,
        String notes,
        CharacterAlignmentResponseDto alignment,
        CharacterTypeResponseDto type,
        CharacterRaceResponseDto race,
        @JsonProperty("class") CharacterClassResponseDto characterClass
) { }