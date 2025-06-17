package de.fi.webapp.services.mapper;

import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.services.model.Person;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity entity);
    PersonEntity convert(Person person);
    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
