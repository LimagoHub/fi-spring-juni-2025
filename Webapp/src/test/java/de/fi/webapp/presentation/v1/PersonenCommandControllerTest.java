package de.fi.webapp.presentation.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.services.PersoneServiceException;
import de.fi.webapp.services.PersonenService;
import de.fi.webapp.services.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PersonenCommandControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService personenServiceMock;

    @Test
    void delete_success() throws PersoneServiceException {
        /*
        if(personenService.loesche(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
         */

        when(personenServiceMock.loesche(any(UUID.class))).thenReturn(true);
        ResponseEntity<Void> result = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(personenServiceMock).loesche(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));
    }

    @Test
    void post_success() throws PersoneServiceException {

        final UUID uuid = UUID.randomUUID();
        final PersonDto personDtoToSend = PersonDto.builder().id(uuid).vorname("John").nachname("Doe").build();
        final Person personToVerify = Person.builder().id(uuid).vorname("John").nachname("Doe").build();

        final HttpEntity<PersonDto> request = new HttpEntity<>(personDtoToSend);

        doNothing().when(personenServiceMock).speichern(any());

        var entity = restTemplate.exchange("/v1/personen", HttpMethod.POST, request, Void.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode() );
        verify(personenServiceMock).speichern(personToVerify);
        assertTrue(entity.getHeaders().getLocation().getPath().endsWith("/v1/personen/" + uuid.toString()));
    }
}