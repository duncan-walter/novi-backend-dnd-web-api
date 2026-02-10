package walter.duncan.dndwebapi.controllers.gameinformation;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.config.openapi.annotations.gameinformation.*;
import walter.duncan.dndwebapi.dtos.gameinformation.equipment.EquipmentRequestDto;
import walter.duncan.dndwebapi.dtos.gameinformation.equipment.EquipmentResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.gameinformation.equipment.EquipmentResponseMapper;
import walter.duncan.dndwebapi.services.gameinformation.EquipmentService;

@GameInformationTag
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final EquipmentResponseMapper mapper;
    private final UrlHelper urlHelper;

    public EquipmentController(EquipmentService equipmentService, EquipmentResponseMapper mapper, UrlHelper urlHelper) {
        this.equipmentService = equipmentService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetByIdGameInformationDocs
    @GetMapping("/{id}")
    public ResponseEntity<@NonNull EquipmentResponseDto> getById(@PathVariable Long id) {
        var responseDto = this.mapper.toDto(this.equipmentService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetGameInformationDocs
    @GetMapping
    public ResponseEntity<@NonNull List<EquipmentResponseDto>> getAll() {
        var responseDtos = this.mapper.toDtos(this.equipmentService.findAll());

        return ResponseEntity.ok(responseDtos);
    }

    @CreateEquipmentDocs
    @PostMapping
    public ResponseEntity<@NonNull EquipmentResponseDto> create(@RequestBody @Valid EquipmentRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.equipmentService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @UpdateEquipmentDocs
    @PutMapping("/{id}")
    public ResponseEntity<@NonNull EquipmentResponseDto> update(@PathVariable Long id, @RequestBody @Valid EquipmentRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.equipmentService.update(id, requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }

    @DeleteGameInformationDocs
    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Object> delete(@PathVariable Long id) {
        this.equipmentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}