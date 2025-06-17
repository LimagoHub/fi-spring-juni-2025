package de.fi.webapp.persistence;

import de.fi.webapp.persistence.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p.nachname from PersonEntity p where p.id = :uuid")
    Iterable<String> xyz(UUID uuid);

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> abc();


    @Query("select new de.fi.webapp.persistence.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findTinies();
}
