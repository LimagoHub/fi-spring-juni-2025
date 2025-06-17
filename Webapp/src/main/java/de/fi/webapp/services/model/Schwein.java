package de.fi.webapp.services.model;

import lombok.*;

import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schwein {

    private UUID id;
    private String name;
    private int gewicht;

    public void fuettern() {
        setGewicht(getGewicht()+1);
    }
}
