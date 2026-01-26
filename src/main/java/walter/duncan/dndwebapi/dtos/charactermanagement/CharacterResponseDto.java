package walter.duncan.dndwebapi.dtos.charactermanagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterResponseDto(
        Long id,
        String name,
        int charisma,
        int constitution,
        int dexterity,
        int intelligence,
        int strength,
        int wisdom,
        int maxHitPoints,
        int currentHitPoints,
        int experiencePoints,
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