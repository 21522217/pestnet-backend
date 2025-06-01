package uit.app.com.pestnet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.exception.NotFoundException;
import uit.app.com.pestnet.model.Pest;
import uit.app.com.pestnet.repository.PestRepository;
import uit.app.com.pestnet.service.PestService;
import uit.app.com.pestnet.mapper.PestMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PestServiceImpl implements PestService {

    private final PestRepository pestRepository;
    private final PestMapper pestMapper;

    @Override
    public List<PestDto> getAllPests() {
        return pestRepository.findAllByIsDeletedFalse()
                .stream()
                .map(pestMapper::toDto)
                .toList();
    }

    @Override
    public PestDto getPestById(UUID id) {
        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.getDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));
        return pestMapper.toDto(pest);
    }

    @Override
    public PestDto createPest(PestDto dto) {
        Pest pest = pestMapper.toEntity(dto);
        pest.setId(null);
        return pestMapper.toDto(pestRepository.save(pest));
    }

    @Override
    public PestDto updatePest(UUID id, PestDto dto) {
        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.getDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));

        pest.setName(dto.getName());
        pest.setRegions(dto.getRegions());
        pest.setScientificName(dto.getScientificName());
        pest.setDescription(dto.getDescription());
        pest.setBiologicalCharacteristics(dto.getBiologicalCharacteristics());
        pest.setControlMethods(dto.getControlMethods());
        pest.setHarmLevel(dto.getHarmLevel());
        pest.setImageUrl(dto.getImageUrl());
        pest.setPestUrl(dto.getPestUrl());
        pest.setPestInsecticide(dto.getPestInsecticide());

        return pestMapper.toDto(pestRepository.save(pest));
    }

    @Override
    public void deletePest(UUID id) {
        Pest pest = pestRepository.findById(id)
                .filter(p -> !p.getDeleted())
                .orElseThrow(() -> new NotFoundException("Pest not found with ID: " + id));

        pest.setDeleted(true);
        pestRepository.save(pest);
    }
}
