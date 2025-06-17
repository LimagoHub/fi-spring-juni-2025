package de.fi.webapp.presentation.v1;


import de.fi.webapp.services.SchweineService;
import de.fi.webapp.services.SchweineServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/schweine")
public class SchweineCommandController {

    private final SchweineService schweineService;

    @PostMapping("/{id}/scripts")
    public ResponseEntity<Void> fuettern(final UUID id, @RequestParam(required = true) String action) throws SchweineServiceException {
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
