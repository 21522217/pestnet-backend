package uit.app.com.pestnet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.dto.ApiResponse;
import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;
import uit.app.com.pestnet.service.ClassificationService;

@RestController
@RequestMapping("/api/classifications")
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationService classificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClassificationResponse>> createClassification(
            @RequestBody ClassificationRequest request
    ) {
        ClassificationResponse response = classificationService.createClassification(request);
        return ResponseEntity.ok(
                ApiResponse.<ClassificationResponse>builder()
                        .status(200)
                        .message("Classification created successfully")
                        .data(response)
                        .build()
        );
    }
}
