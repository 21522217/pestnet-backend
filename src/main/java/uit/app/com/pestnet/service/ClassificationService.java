package uit.app.com.pestnet.service;

import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;

import java.util.List;
import java.util.UUID;

public interface ClassificationService {
    ClassificationResponse createClassification(ClassificationRequest request, UUID userId);
    void softDeleteClassification(UUID classificationId, UUID userId);
    List<ClassificationResponse> getRecentClassifications(UUID userId);
    List<ClassificationResponse> getBestConfidenceClassifications(UUID userId);
    ClassificationResponse getClassificationById(UUID id, UUID userId);

}
