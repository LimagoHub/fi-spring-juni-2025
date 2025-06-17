package de.fi.webapp.presentation.mapper;


import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.services.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    Person convert(PersonDto dto);
    PersonDto convert(Person person);
    Iterable<PersonDto> convert(Iterable<Person> personen);
}
