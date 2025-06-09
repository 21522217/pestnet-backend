package uit.app.com.pestnet.mapper;

import org.mapstruct.*;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.model.Pest;

@Mapper(componentModel = "spring")
public interface PestMapper {
    PestDto toDto(Pest pest);
    Pest toEntity(PestDto dto);
}
