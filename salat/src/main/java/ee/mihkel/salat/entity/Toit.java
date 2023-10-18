package ee.mihkel.salat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//{nimetus: "Kartulisalat, toidukomponendid: [
// {toiduaine: {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}, kogus: 100},
// {toiduaine: {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}, kogus: 100},
// {toiduaine: {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}, kogus: 100}
// ]}
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Toit {
    @Id
    private String nimetus;

    @ManyToMany
    private List<Toidukomponent> toidukomponendid;

    public double saaValgud() {
        double valgud = 0;
        for (Toidukomponent t: this.toidukomponendid) {
            valgud += t.getKogus() * t.getToiduaine().getValgud() / 100;
        }
        return valgud;
    }
}
