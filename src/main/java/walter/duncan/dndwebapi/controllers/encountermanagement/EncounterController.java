package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.Set;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterResponseDto;
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
    public ResponseEntity<@NonNull EncounterResponseDto> create(@RequestBody @Valid EncounterRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.encounterService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<@NonNull Object> addParticipant(@PathVariable Long id, @RequestBody @Valid Object requestDto) {
        var location = this.urlHelper.getResourceUri(0L); // Placeholder

        return ResponseEntity
                .created(location)
                .body(""); // Placeholder
    }

    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull Object> advanceTurn(@PathVariable Long id, @RequestBody @Valid Object requestDto) {
        return ResponseEntity.ok(""); // Placeholder
    }
}