package de.fi.webapp.demo;


import de.fi.webapp.mail.MailConnector;
import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {


    private final MailConnector mailConnector;

    @PostConstruct
    public void doIt() {
        mailConnector.send();
    }
}
