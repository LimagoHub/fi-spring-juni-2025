package de.fi.webapp.services.inner;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.services.BlacklistService;
import de.fi.webapp.services.model.Person;
import de.fi.webapp.services.PersoneServiceException;
import de.fi.webapp.services.PersonenService;
import de.fi.webapp.services.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersoneServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository personenRepository;
    private final PersonMapper personMapper;
    //private final BlacklistService blacklistService;
    @Qualifier("antipathen") // Lomboktrick mit lombok.config file
    private final List<String> antipathen;



    @Override
    public void speichern(final Person person) throws PersoneServiceException {
        try {
            checkPerson(person);
            personenRepository.save(personMapper.convert(person));
            //event
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten", e);
        }
    }

    private void checkPerson(final Person person) throws PersoneServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(final Person person) throws PersoneServiceException {
        if(antipathen.contains(person.getVorname()) ) {
            throw new PersoneServiceException("Unerwuenschte Person");
        }
    }

    private static void validatePerson(final Person person) throws PersoneServiceException {
        if(person == null) {
            throw new PersoneServiceException("Personen darf nicht null sein");
        }
        if(person.getVorname() == null || person.getVorname().length() < 2) {
            throw new PersoneServiceException("Vorname zu kurz");
        }
        if(person.getNachname() == null || person.getNachname().length() < 2) {
            throw new PersoneServiceException("Nachname zu kurz");
        }
    }

    @Override
    public boolean aendern(final Person person) throws PersoneServiceException {
        try {



            if(person == null) {
                throw new PersoneServiceException("Personen darf nicht null sein");
            }
            if(! personenRepository.existsById(person.getId())) return false;
            if(person.getVorname() == null || person.getVorname().length() < 2) {
                throw new PersoneServiceException("Vorname zu kurz");
            }
            if(person.getNachname() == null || person.getNachname().length() < 2) {
                throw new PersoneServiceException("Nachname zu kurz");
            }
            businessCheck(person);
            personenRepository.save(personMapper.convert(person));
            return true;
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten", e);
        }
    }

    @Override
    public boolean loesche(final UUID id) throws PersoneServiceException {
        try {
            if(personenRepository.existsById(id)) {
                personenRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten", e);
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersoneServiceException {
        try {
           return personenRepository.findById(id).map(personMapper::convert);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersoneServiceException {
        try {
            return personMapper.convert(personenRepository.findAll());
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten", e);
        }
    }
}
