package uit.app.com.pestnet.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uit.app.com.pestnet.dto.PestDto;
import uit.app.com.pestnet.model.Pest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T21:59:24+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class PestMapperImpl implements PestMapper {

    @Override
    public PestDto toDto(Pest pest) {
        if ( pest == null ) {
            return null;
        }

        PestDto pestDto = new PestDto();

        return pestDto;
    }

    @Override
    public Pest toEntity(PestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pest pest = new Pest();

        return pest;
    }
}
