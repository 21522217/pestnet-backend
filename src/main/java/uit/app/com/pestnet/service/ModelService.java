package uit.app.com.pestnet.service;

import uit.app.com.pestnet.dto.ModelRequest;
import uit.app.com.pestnet.dto.ModelResponse;

import java.util.List;

public interface ModelService {
    ModelResponse createModel(ModelRequest request);
    List<ModelResponse> getAllModels();
}
