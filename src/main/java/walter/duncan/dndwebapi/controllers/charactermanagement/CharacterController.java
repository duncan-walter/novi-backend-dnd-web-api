package walter.duncan.dndwebapi.controllers.charactermanagement;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterResponseMapper;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterResponseMapper mapper;
    private final UrlHelper urlHelper;

    public CharacterController(CharacterService characterService, CharacterResponseMapper mapper, UrlHelper urlHelper) {
        this.characterService = characterService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull CharacterResponseDto> getById(@PathVariable Long id) {
        var responseDto = this.mapper.toDto(this.characterService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<@NonNull List<CharacterResponseDto>> get() {
        var responseDtos = this.mapper.toDtos(this.characterService.findAll());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<@NonNull CharacterResponseDto> create(@RequestBody @Valid CharacterRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.characterService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull CharacterResponseDto> update(@PathVariable Long id, @RequestBody @Valid CharacterRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.characterService.update(id, requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.id());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Object> delete(@PathVariable Long id) {
        this.characterService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}