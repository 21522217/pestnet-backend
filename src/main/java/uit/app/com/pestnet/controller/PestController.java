package uit.app.com.pestnet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.LivenessState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.dto.ApiResponse;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.service.PestService;
import uit.app.com.pestnet.util.ApiResponseUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pests")
@RequiredArgsConstructor
public class PestController {

    private final PestService pestService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PestDto>>> getAllPests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<PestDto> pestPage = pestService.getAllPests(pageable);
        return ResponseEntity.ok(
                ApiResponseUtil.paginated(
                        pestPage.getContent(),
                        "Fetched pest list with pagination",
                        HttpStatus.OK,
                        pestPage
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PestDto> getPestById(@PathVariable UUID id) {
        return ResponseEntity.ok(pestService.getPestById(id));
    }

    @GetMapping("/scientificName/{scientificName}")
    public ResponseEntity<PestDto> getPestByScientificName(@PathVariable String scientificName) {
        return ResponseEntity.ok(pestService.getPestByScientificName(scientificName));
    }

    @PostMapping
    public ResponseEntity<PestDto> createPest(@Valid @RequestBody PestDto dto) {
        return ResponseEntity.ok(pestService.createPest(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<PestDto>> createPests(@Valid @RequestBody List<PestDto> pestDtos) {
        return ResponseEntity.ok(pestService.createPests(pestDtos));
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
