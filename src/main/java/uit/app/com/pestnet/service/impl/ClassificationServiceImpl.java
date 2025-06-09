package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;
import uit.app.com.pestnet.exception.NotFoundException;
import uit.app.com.pestnet.model.*;
import uit.app.com.pestnet.repository.*;
import uit.app.com.pestnet.service.ClassificationService;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;
    private final ImageRepository imageRepository;
    private final PestRepository pestRepository;
    private final ModelRepository modelRepository;

    @Override
    public ClassificationResponse createClassification(ClassificationRequest request) {
        Image image = Image.builder()
                .imageUrl(request.getPestUrl())
                .uploadedAt(LocalDateTime.now())
                .originalName("default.jpg")
                .isDeleted(false)
                .cropped(false)
                .build();
        image = imageRepository.save(image);

        Pest pest = pestRepository.findByName(request.getPestName())
                .orElseThrow(() -> new NotFoundException("Pest not found: " + request.getPestName()));

        Model model = modelRepository.findByModelName(request.getModelName())
                .orElseThrow(() -> new NotFoundException("Model not found: " + request.getModelName()));

        Classification classification = Classification.builder()
                .image(image)
                .pest(pest)
                .model(model)
                .confidence(request.getConfidence())
                .classifiedAt(LocalDateTime.now())
                .build();

        Classification saved = classificationRepository.save(classification);

        return ClassificationResponse.builder()
                .classificationId(saved.getId())
                .pestId(pest.getId())
                .pestName(pest.getName())
                .modelName(model.getModelName())
                .confidence(saved.getConfidence())
                .classifiedAt(saved.getClassifiedAt().atZone(ZoneId.systemDefault()).toInstant())
                .imageUrl(image.getImageUrl())
                .pestRegions(pest.getRegions())
                .pestScientificName(pest.getScientificName())
                .pestDescription(pest.getDescription())
                .pestUrl(pest.getPestUrl())
                .pestInsecticide(pest.getPestInsecticide())
                .build();
    }
}
