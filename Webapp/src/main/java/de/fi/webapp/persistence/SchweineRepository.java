package de.fi.webapp.persistence;

import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.services.model.Schwein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweineRepository extends CrudRepository<SchweinEntity, UUID> {
}
