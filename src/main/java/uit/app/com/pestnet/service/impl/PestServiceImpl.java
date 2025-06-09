package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.exception.BadRequestException;
import uit.app.com.pestnet.exception.NotFoundException;
import uit.app.com.pestnet.mapper.PestMapper;
import uit.app.com.pestnet.model.Pest;
import uit.app.com.pestnet.repository.PestRepository;
import uit.app.com.pestnet.service.PestService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PestServiceImpl implements PestService {

    private final PestRepository pestRepository;
    private final PestMapper pestMapper;

    @Override
    public Page<PestDto> getAllPests(Pageable pageable) {
        return pestRepository.findAllByDeletedFalse(pageable)
                .map(pestMapper::toDto);
    }

    @Override
    public PestDto getPestById(UUID id) {
        if (id == null) throw new BadRequestException("Pest ID must not be null");
        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));
        return pestMapper.toDto(pest);
    }

    @Override
    public PestDto getPestByScientificName(String scientificName) {
        if (scientificName == null || scientificName.isBlank()) {
            throw new BadRequestException("Scientific name must not be null or blank");
        }

        Pest pest = pestRepository.findByScientificNameIgnoreCaseAndDeletedFalse(scientificName)
                .orElseThrow(() -> new NotFoundException("Pest not found with scientific name: " + scientificName));

        return pestMapper.toDto(pest);
    }

    @Override
    @Transactional
    public PestDto createPest(PestDto dto) {
        if (dto == null) throw new BadRequestException("Pest data must not be null");

        try {
            Pest pest = pestMapper.toEntity(dto);
            pest.setId(null); // Ensure creation
            Pest saved = pestRepository.save(pest);
            return pestMapper.toDto(saved);
        } catch (Exception e) {
            throw new BadRequestException("Failed to create pest: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<PestDto> createPests(List<PestDto> pestDtos) {
        if (pestDtos == null || pestDtos.isEmpty()) {
            throw new BadRequestException("Pest list must not be empty");
        }

        try {
            List<Pest> pests = pestDtos.stream()
                    .map(pestMapper::toEntity)
                    .peek(p -> p.setId(null))
                    .toList();

            List<Pest> savedPests = pestRepository.saveAll(pests);
            return savedPests.stream().map(pestMapper::toDto).toList();
        } catch (Exception e) {
            throw new BadRequestException("Failed to create pests: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public PestDto updatePest(UUID id, PestDto dto) {
        if (id == null || dto == null)
            throw new BadRequestException("Pest ID and data must not be null");

        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));

        try {
            pest.setName(dto.getName());
            pest.setRegions(dto.getRegions());
            pest.setScientificName(dto.getScientificName());
            pest.setDescription(dto.getDescription());
            pest.setBiologicalCharacteristics(dto.getBiologicalCharacteristics());
            pest.setControlMethods(dto.getControlMethods());
            pest.setHarmLevel(dto.getHarmLevel());
            pest.setPestUrl(dto.getPestUrl());
            pest.setPestInsecticide(dto.getPestInsecticide());

            return pestMapper.toDto(pestRepository.save(pest));
        } catch (Exception e) {
            throw new BadRequestException("Failed to update pest: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deletePest(UUID id) {
        if (id == null) throw new BadRequestException("Pest ID must not be null");

        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));

        pest.setDeleted(true);
        pestRepository.save(pest);
    }
}
