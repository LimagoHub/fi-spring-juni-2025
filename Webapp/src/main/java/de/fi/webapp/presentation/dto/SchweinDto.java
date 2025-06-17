package de.fi.webapp.presentation.dto;

import jakarta.validation.constraints.DecimalMin;

import java.util.UUID;

public class SchweinDto {

    private UUID id;
    private String name;

    @DecimalMin("10")
    private int gewicht;
}
