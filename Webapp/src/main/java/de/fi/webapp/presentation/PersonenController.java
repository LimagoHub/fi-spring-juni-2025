package de.fi.webapp.presentation;

import de.fi.webapp.presentation.dto.PersonDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {

    @GetMapping(value = "/xyz", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto getPerson() {
        return PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Doe")
                .build();
    }
}
