package de.fi.webapp.persistence.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_personen")
public class PersonEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 50)
    private String vorname;

    @Column(nullable = false, length = 50)
    private String nachname;


}
