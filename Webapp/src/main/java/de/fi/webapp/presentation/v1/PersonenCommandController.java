package de.fi.webapp.presentation.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenCommandController {

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        if(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6").equals(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder uriBuilder)  {
        System.out.println(personDto);
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto personDto)  {

        if(! id.equals(personDto.getId())) throw new IllegalArgumentException("Upps");

        System.out.println(personDto);

        return ResponseEntity.ok().build();
    }
}
