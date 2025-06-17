package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uit.app.com.pestnet.dto.ClassificationRequest;
import uit.app.com.pestnet.dto.ClassificationResponse;
import uit.app.com.pestnet.exception.NotFoundException;
import uit.app.com.pestnet.model.*;
import uit.app.com.pestnet.repository.*;
import uit.app.com.pestnet.service.ClassificationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;
    private final ImageRepository imageRepository;
    private final PestRepository pestRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ClassificationResponse createClassification(ClassificationRequest request, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Image image = Image.builder()
                .user(user)
                .imageUrl(request.getImageUrl())
                .originalName(request.getOriginalName())
                .isDeleted(false)
                .cropped(false)
                .build();

        image = imageRepository.save(image);


        Pest pest = findPestByName(request.getScientificName());

        Model model = modelRepository.findByModelName(request.getModelName())
                .orElseThrow(() -> new NotFoundException("Model not found"));

        Classification classification = Classification.builder()
                .image(image)
                .pest(pest)
                .model(model)
                .confidence(request.getConfidence())
                .build();

        classification = classificationRepository.save(classification);
        return ClassificationResponse.fromEntity(classification);
    }

    /**
     * Find pest by scientific name first, then by common name if not found
     */
    private Pest findPestByName(String pestName) {

        return pestRepository.findByScientificName(pestName)
                .or(() -> pestRepository.findByName(pestName))
                .or(() -> pestRepository.findByScientificNameIgnoreCase(pestName))
                .or(() -> pestRepository.findByNameIgnoreCase(pestName))
                .orElseThrow(() -> new NotFoundException("Pest not found with name: " + pestName));
    }

    @Override
    @Transactional
    public void softDeleteClassification(UUID classificationId, UUID userId) {
        Classification classification = classificationRepository.findById(classificationId)
                .orElseThrow(() -> new NotFoundException("Classification not found"));

        Image image = classification.getImage();
        if (!image.getUser().getId().equals(userId)) {
            throw new SecurityException("Unauthorized access to delete this classification");
        }

        image.setDeleted(true);
        imageRepository.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassificationResponse> getRecentClassifications(UUID userId) {
        return classificationRepository
                .findByImage_User_IdAndImage_IsDeletedFalseOrderByClassifiedAtDesc(userId)
                .stream()
                .limit(10)
                .map(ClassificationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassificationResponse> getBestConfidenceClassifications(UUID userId) {
        return classificationRepository.findByImage_User_IdAndImage_IsDeletedFalseOrderByConfidenceDesc(userId).stream()
                .map(ClassificationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClassificationResponse getClassificationById(UUID id, UUID userId) {
        Classification classification = classificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Classification not found"));


        if (!classification.getImage().getUser().getId().equals(userId)) {
            throw new SecurityException("Unauthorized access to this classification");
        }

        return ClassificationResponse.fromEntity(classification);
    }
}