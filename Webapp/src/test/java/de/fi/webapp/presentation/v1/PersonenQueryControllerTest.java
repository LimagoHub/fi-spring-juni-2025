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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService personenServiceMock;



    @Test
    void findByIdTest() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());


        // Recordmode
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);
        // Replaymode


        var result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        assertEquals("John", result.getVorname() );
    }

    @Test
    void zwei() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);

        var result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(result);
    }

    @Test
    void drei() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);

        var result = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        var body = result.getBody();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("John", body.getVorname() );
    }

    @Test
    void vier() throws Exception {
        final Optional<Person> optionalPerson = Optional.empty();
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);

        var result = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    @Test
    void fuenf() throws Exception {
        var personen = List.of(
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Rambo").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Wayne").build()
        );

        PersonDto nurZurDemonstration = PersonDto.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build();
        HttpEntity entityToUpload = new HttpEntity<>(nurZurDemonstration);

        when(personenServiceMock.findeAlle()).thenReturn(personen);

        var result = restTemplate.exchange("/v1/personen", HttpMethod.GET, entityToUpload, new ParameterizedTypeReference<List<PersonDto>>() { });

        assertEquals(HttpStatus.OK, result.getStatusCode());
        result.getBody().forEach(b->assertEquals("John", b.getVorname()));
        assertEquals(result.getBody().size() , 3);

    }
}