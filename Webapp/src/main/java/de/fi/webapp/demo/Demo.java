package de.fi.webapp.demo;


import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonenRepository personenRepository;

    @PostConstruct
    public void doIt() {
        personenRepository.findTinies().forEach(System.out::println);
    }
}
