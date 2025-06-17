package de.fi.webapp.services;

import de.fi.webapp.services.model.Person;
import de.fi.webapp.services.model.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein person) throws SchweineServiceException ;
    boolean aendern(Schwein person) throws SchweineServiceException ;
    boolean loesche(UUID id) throws SchweineServiceException ;
    Optional<Schwein> findeNachId(UUID id) throws SchweineServiceException ;
    Iterable<Schwein> findeAlle() throws SchweineServiceException ;
    boolean fuettern(UUID id) throws SchweineServiceException ;
}
