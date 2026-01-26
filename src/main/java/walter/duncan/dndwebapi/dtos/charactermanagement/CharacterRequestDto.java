package walter.duncan.dndwebapi.dtos.charactermanagement;

public record CharacterRequestDto(
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
        int copperPieces,
        int silverPieces,
        int electrumPieces,
        int goldPieces,
        int platinumPieces,
        String notes,
        String alignmentCode,
        Long typeId,
        Long raceId,
        Long classId
) { }