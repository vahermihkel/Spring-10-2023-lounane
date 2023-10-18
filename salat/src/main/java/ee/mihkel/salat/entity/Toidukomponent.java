package ee.mihkel.salat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// {toiduaine: {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}, kogus: 100}
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Toidukomponent {
    @Id
    private int id;

    @ManyToOne
    private Toiduaine toiduaine;
    private int kogus;
}

// Kasutaja
// Profiil
// @OneToOne
// Kontaktandmed   tel, aadress, postiindeks, riik, maakond, linn, tänav, tänavanumber, korterinumber

//
