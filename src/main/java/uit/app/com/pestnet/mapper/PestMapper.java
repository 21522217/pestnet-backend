package uit.app.com.pestnet.mapper;

import org.mapstruct.*;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.model.Pest;

@Mapper(componentModel = "spring")
public interface PestMapper {

    PestDto toDto(Pest pest);

    @Mapping(target = "id", ignore = true)
    Pest toEntity(PestDto dto);

    @AfterMapping
    default void afterToEntity(PestDto dto, @MappingTarget Pest pest) {
        if (pest.getPestUrl() == null) {
            pest.setPestUrl("https://www.inaturalist.org/taxa/52045-Cnaphalocrocis-medinalis");
        }

        pest.setDeleted(dto.getDeleted() != null ? dto.getDeleted() : false);
    }

    @AfterMapping
    default void afterToDto(Pest pest, @MappingTarget PestDto dto) {
        dto.setDeleted(pest.getDeleted());
    }
}
