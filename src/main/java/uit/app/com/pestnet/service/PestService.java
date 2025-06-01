package uit.app.com.pestnet.service;

import uit.app.com.pestnet.dto.PestDto;

import java.util.List;
import java.util.UUID;

public interface PestService {
    List<PestDto> getAllPests();
    PestDto getPestById(UUID id);
    PestDto createPest(PestDto dto);
    PestDto updatePest(UUID id, PestDto dto);
    void deletePest(UUID id);
}
