package de.fi.webapp.services;


import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.services.inner.PersonenServiceImpl;
import de.fi.webapp.services.mapper.PersonMapper;
import de.fi.webapp.services.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    @Scope("singleton")
    @Lazy(false)
    @Qualifier("antipathen")
    public List<String> getBlacklistedPersons() {
        return List.of("Attila", "Peter","Paul", "Mary");
    }
    @Bean
    @Scope("singleton")
    @Lazy(false)
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana", "Cherry","Strawberry", "Raspberry");
    }

    /*@Bean
    public PersonenService createPersonenService(PersonenRepository personenRepository, PersonMapper mapper, @Qualifier("antipathen") List<String> blacklistedPersons) {
        return new PersonenServiceImpl(personenRepository, mapper, blacklistedPersons);
    }*/
}
