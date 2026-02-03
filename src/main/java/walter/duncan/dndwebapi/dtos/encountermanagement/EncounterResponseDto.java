package walter.duncan.dndwebapi.dtos.encountermanagement;

import java.util.List;

public record EncounterResponseDto(
        Long id,
        String title,
        String description,
        int roundNumber,
        String state,
        EncounterParticipantResponseDto currentActor,
        List<EncounterParticipantResponseDto> participants
) { }