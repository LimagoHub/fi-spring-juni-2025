package de.fi.webapp.services.inner;

import de.fi.webapp.persistence.SchweineRepository;
import de.fi.webapp.services.SchweineService;
import de.fi.webapp.services.SchweineServiceException;
import de.fi.webapp.services.mapper.SchweinMapper;
import de.fi.webapp.services.model.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Service
@Transactional(rollbackFor = SchweineServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {


    private final SchweineRepository repository;
    private final SchweinMapper mapper;

    @Override
    public void speichern(final Schwein person) throws SchweineServiceException {

    }

    @Override
    public boolean aendern(final Schwein person) throws SchweineServiceException {
        return false;
    }

    @Override
    public boolean loesche(final UUID id) throws SchweineServiceException {
        return false;
    }

    @Override
    public Optional<Schwein> findeNachId(final UUID id) throws SchweineServiceException {
        return Optional.empty();
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        return null;
    }

    @Override
    public boolean fuettern(final UUID id) throws SchweineServiceException {
        Optional<Schwein> schweinOptional = findeNachId(id);
        if (schweinOptional.isPresent()) {
            Schwein schwein = schweinOptional.get();
            schwein.fuettern();
            aendern(schwein);
            return true;
        }
        return false;
    }
}
