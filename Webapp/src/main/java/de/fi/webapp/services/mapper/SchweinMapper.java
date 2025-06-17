package de.fi.webapp.services.mapper;

import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.services.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity schweinEntity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> schweinEntities);
}
