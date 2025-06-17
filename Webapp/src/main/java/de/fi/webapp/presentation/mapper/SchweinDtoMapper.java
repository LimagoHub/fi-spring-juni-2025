package de.fi.webapp.presentation.mapper;


import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.presentation.dto.SchweinDto;
import de.fi.webapp.services.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {
    Schwein convert(SchweinDto schweinDto);
    SchweinDto convert(Schwein schwein);
    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
