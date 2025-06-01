package uit.app.com.pestnet.service;

import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;
import uit.app.com.pestnet.model.User;

public interface ClassificationService {
    ClassificationResponse createClassification(ClassificationRequest request);
}
