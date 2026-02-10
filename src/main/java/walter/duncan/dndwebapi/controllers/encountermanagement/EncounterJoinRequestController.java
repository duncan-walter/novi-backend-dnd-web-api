package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.config.openapi.annotations.encountermanagement.*;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestResponseDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestStateUpdateRequestDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterJoinRequestResponseMapper;
import walter.duncan.dndwebapi.services.encountermanagement.EncounterJoinRequestService;

@EncounterManagementTag
@RestController
@RequestMapping("/encounters/{encounterId}/join-requests")
public class EncounterJoinRequestController {
    private final EncounterJoinRequestService encounterJoinRequestService;
    private final EncounterJoinRequestResponseMapper mapper;
    private final UrlHelper urlHelper;

    public EncounterJoinRequestController(
            EncounterJoinRequestService encounterJoinRequestService,
            EncounterJoinRequestResponseMapper mapper,
            UrlHelper urlHelper
    ) {
        this.encounterJoinRequestService = encounterJoinRequestService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetEncounterJoinRequestDocs
    @GetMapping
    public ResponseEntity<@NonNull List<EncounterJoinRequestResponseDto>> get(
            @PathVariable Long encounterId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(this.mapper.toDtos(this.encounterJoinRequestService.findAllByEncounterId(encounterId, jwt)));
    }

    @CreateEncounterJoinRequestDocs
    @PostMapping
    public ResponseEntity<@NonNull EncounterJoinRequestResponseDto> create(
            @PathVariable Long encounterId,
            @RequestBody @Valid EncounterJoinRequestRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var responseDto = this.mapper.toDto(this.encounterJoinRequestService.create(encounterId, requestDto, jwt));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @UpdateEncounterJoinRequestStateDocs
    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull EncounterJoinRequestResponseDto> updateState(
            @PathVariable Long encounterId,
            @PathVariable Long id,
            @RequestBody @Valid EncounterJoinRequestStateUpdateRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var responseDto = this.mapper.toDto(this.encounterJoinRequestService.updateState(encounterId, id, requestDto, jwt));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }
}