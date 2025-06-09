package uit.app.com.pestnet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uit.app.com.pestnet.dto.PestDto;

import java.util.List;
import java.util.UUID;

public interface PestService {
    Page<PestDto> getAllPests(Pageable pageable);
    PestDto getPestById(UUID id);
    PestDto createPest(PestDto dto);
    PestDto getPestByScientificName(String scientificName);

    List<PestDto> createPests(List<PestDto> pestDtos);

    PestDto updatePest(UUID id, PestDto dto);
    void deletePest(UUID id);
}
