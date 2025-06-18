package de.fi.webapp.services;

import de.fi.webapp.services.model.Person;

public interface BlacklistService {

    boolean isBlacklisted(final Person possibleBlacklistedPerson);
}
