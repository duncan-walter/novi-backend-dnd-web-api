package walter.duncan.dndwebapi.dtos.charactermanagement;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import walter.duncan.dndwebapi.dtos.charactermanagement.inventory.CharacterInventoryItemRequestDto;

public record CharacterRequestDto(
        @NotBlank
        @Size(max = 50) String name,
        @Min(1) @Max(30) int charisma,
        @Min(1) @Max(30) int constitution,
        @Min(1) @Max(30) int dexterity,
        @Min(1) @Max(30) int intelligence,
        @Min(1) @Max(30) int strength,
        @Min(1) @Max(30) int wisdom,
        @Min(1) int maxHitPoints,
        @Min(1) int currentHitPoints,
        @PositiveOrZero int experiencePoints,
        @Size(max = 50) String size,
        @PositiveOrZero int copperPieces,
        @PositiveOrZero int silverPieces,
        @PositiveOrZero int electrumPieces,
        @PositiveOrZero int goldPieces,
        @PositiveOrZero int platinumPieces,
        @Size(max = 5000) String notes,
        @NotBlank String alignmentCode,
        @NotNull Long typeId,
        @NotNull Long raceId,
        @NotNull Long classId,
        @Valid List<CharacterInventoryItemRequestDto> inventory
) { }