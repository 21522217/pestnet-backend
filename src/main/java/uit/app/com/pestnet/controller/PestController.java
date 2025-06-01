package uit.app.com.pestnet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.service.PestService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pests")
@RequiredArgsConstructor
public class PestController {

    private final PestService pestService;

    @GetMapping
    public ResponseEntity<List<PestDto>> getAllPests() {
        return ResponseEntity.ok(pestService.getAllPests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PestDto> getPestById(@PathVariable UUID id) {
        return ResponseEntity.ok(pestService.getPestById(id));
    }

    @PostMapping
    public ResponseEntity<PestDto> createPest(@Valid @RequestBody PestDto dto) {
        return ResponseEntity.ok(pestService.createPest(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PestDto> updatePest(@PathVariable UUID id, @RequestBody PestDto dto) {
        return ResponseEntity.ok(pestService.updatePest(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePest(@PathVariable UUID id) {
        pestService.deletePest(id);
        return ResponseEntity.noContent().build();
    }
}
