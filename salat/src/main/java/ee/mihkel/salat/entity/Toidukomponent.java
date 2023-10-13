package ee.mihkel.salat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

// {toiduaine: {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}, kogus: 100}
@Getter
@AllArgsConstructor
public class Toidukomponent {
    private int id;
    private Toiduaine toiduaine;
    private int kogus;
}
