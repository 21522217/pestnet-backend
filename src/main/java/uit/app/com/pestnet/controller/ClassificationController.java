package uit.app.com.pestnet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.config.CustomUserDetails;
import uit.app.com.pestnet.dto.ApiResponse;
import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;
import uit.app.com.pestnet.service.ClassificationService;
import uit.app.com.pestnet.util.ApiResponseUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classifications")
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationService classificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClassificationResponse>> createClassification(
            @Valid @RequestBody ClassificationRequest request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        ClassificationResponse response = classificationService.createClassification(request, user.getUser().getId());
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        response,
                        "Classification created successfully",
                        HttpStatus.CREATED
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> softDeleteClassification(
            @PathVariable UUID id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        classificationService.softDeleteClassification(id, user.getUser().getId());
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        null,
                        "Classification deleted successfully",
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/recent")
    public ResponseEntity<ApiResponse<List<ClassificationResponse>>> getRecentClassifications(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        List<ClassificationResponse> classifications = classificationService.getRecentClassifications(user.getUser().getId());
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        classifications,
                        "Fetched recent classifications successfully",
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/best")
    public ResponseEntity<ApiResponse<List<ClassificationResponse>>> getBestConfidenceClassifications(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        List<ClassificationResponse> classifications = classificationService.getBestConfidenceClassifications(user.getUser().getId());
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        classifications,
                        "Fetched best confidence classifications successfully",
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClassificationResponse>> getClassificationById(
            @PathVariable UUID id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        // You might want to add this method to your service
        ClassificationResponse classification = classificationService.getClassificationById(id, user.getUser().getId());
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        classification,
                        "Classification fetched successfully",
                        HttpStatus.OK
                )
        );
    }
}