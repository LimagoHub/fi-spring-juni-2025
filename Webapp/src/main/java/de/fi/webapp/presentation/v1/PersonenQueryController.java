package de.fi.webapp.presentation.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")

public class PersonenQueryController {



    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) {

        if(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6").equals(id))
            return ResponseEntity.ok(PersonDto.builder()
                    .id(id)
                    .vorname("John")
                    .nachname("Doe")
                    .build());
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDto>> findeAlle(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname
            ,@RequestParam(required = false, defaultValue = "Schmidt")String nachname
    ) {

        System.out.println(vorname + " " + nachname);
      var liste = List.of(
              PersonDto.builder()
              .id(UUID.randomUUID())
              .vorname("John")
              .nachname("Doe")
              .build() ,
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Wick")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("McClaine")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Rambo")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Wayne")
                .build());

      return ResponseEntity.ok(liste);
    };



}
