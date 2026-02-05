package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.Set;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterResponseDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterParticipantRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterActionRequestDto;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterResponseMapper;
import walter.duncan.dndwebapi.services.encountermanagement.EncounterService;

@RestController
@RequestMapping("/encounters")
public class EncounterController {
    private final EncounterService encounterService;
    private final EncounterResponseMapper mapper;
    private final UrlHelper urlHelper;

    public EncounterController(
            EncounterService encounterService,
            EncounterResponseMapper mapper,
            UrlHelper urlHelper
    ) {
        this.encounterService = encounterService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull EncounterResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.mapper.toDto(this.encounterService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<@NonNull Set<EncounterResponseDto>> get() {
        return ResponseEntity.ok(this.mapper.toDtos(this.encounterService.findAll()));
    }

    @PostMapping
    public ResponseEntity<@NonNull EncounterResponseDto> create(
            @RequestBody @Valid EncounterRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var responseDto = this.mapper.toDto(this.encounterService.create(requestDto, jwt));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<@NonNull EncounterResponseDto> addParticipant(
            @PathVariable Long id,
            @RequestBody @Valid EncounterParticipantRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var responseDto = this.mapper.toDto(this.encounterService.addParticipant(id, requestDto, jwt));
        var participantId = responseDto.participants().stream()
                .filter(p -> p.characterId().equals(requestDto.characterId()))
                .findFirst()
                .orElseThrow()
                .id();
        var location = this.urlHelper.getResourceUri(participantId);

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull EncounterResponseDto> action(
            @PathVariable Long id,
            @RequestBody @Valid EncounterActionRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var responseDto = this.mapper.toDto(this.encounterService.performAction(id, requestDto, jwt));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }
}