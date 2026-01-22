package walter.duncan.dndwebapi.controllers;

import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walter.duncan.dndwebapi.dtos.EquipmentRequestDto;
import walter.duncan.dndwebapi.dtos.EquipmentResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.EquipmentResponseMapper;
import walter.duncan.dndwebapi.services.EquipmentService;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull EquipmentResponseDto> getById(@PathVariable Long id) {
        var responseDto = this.mapper.toDto(this.equipmentService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<@NonNull List<EquipmentResponseDto>> getAll() {
        var responseDtos = this.mapper.toDtos(this.equipmentService.findAll());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<@NonNull EquipmentResponseDto> create(@RequestBody @Valid EquipmentRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.equipmentService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull EquipmentResponseDto> update(@PathVariable Long id, @RequestBody @Valid EquipmentRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.equipmentService.update(id, requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Object> delete(@PathVariable Long id) {
        this.equipmentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}