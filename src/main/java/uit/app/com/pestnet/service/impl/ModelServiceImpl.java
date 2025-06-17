package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.dto.ModelRequest;
import uit.app.com.pestnet.dto.ModelResponse;
import uit.app.com.pestnet.model.Model;
import uit.app.com.pestnet.repository.ModelRepository;
import uit.app.com.pestnet.service.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Override
    public ModelResponse createModel(ModelRequest request) {
        Model model = Model.builder()
                .modelSrc(request.getModelSrc())
                .modelName(request.getModelName())
                .build();
        return ModelResponse.fromEntity(modelRepository.save(model));
    }

    @Override
    public List<ModelResponse> getAllModels() {
        return modelRepository.findAll().stream()
                .map(ModelResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
