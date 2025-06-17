package uit.app.com.pestnet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.dto.ApiResponse;
import uit.app.com.pestnet.dto.ModelRequest;
import uit.app.com.pestnet.dto.ModelResponse;
import uit.app.com.pestnet.service.ModelService;
import uit.app.com.pestnet.util.ApiResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ApiResponse<ModelResponse>> createModel(
            @Valid @RequestBody ModelRequest request
    ) {
        ModelResponse response = modelService.createModel(request);
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        response,
                        "Model created successfully",
                        HttpStatus.CREATED
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ModelResponse>>> getAllModels() {
        List<ModelResponse> models = modelService.getAllModels();
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        models,
                        "Fetched all models successfully",
                        HttpStatus.OK
                )
        );
    }
}
