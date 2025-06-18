package de.fi.webapp.presentation.v1;


import de.fi.webapp.presentation.dto.SchweinDto;
import de.fi.webapp.presentation.mapper.SchweinDtoMapper;
import de.fi.webapp.services.SchweineService;
import de.fi.webapp.services.SchweineServiceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweinCommandController {
    private final SchweineService schweineService;
    private final SchweinDtoMapper schweinDTOMapper;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws SchweineServiceException {

        if(schweineService.loesche(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody SchweinDto person, UriComponentsBuilder builder) throws SchweineServiceException{

        schweineService.speichern(schweinDTOMapper.convert(person));
        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());


        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid  @RequestBody SchweinDto person) throws SchweineServiceException{

        if(schweineService.aendern(schweinDTOMapper.convert(person)))
            return ResponseEntity.ok().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/scripts")
    public ResponseEntity<Void> fuettern(final UUID id, @RequestParam(required = true) String action) throws de.fi.webapp.services.SchweineServiceException {
        switch (action) {
            default:
                if(schweineService.fuettern(id)) {
                    return ResponseEntity.ok().build();
                }
                return ResponseEntity.notFound().build();
        }
        //return ResponseEntity.internalServerError().build();
    }
}
