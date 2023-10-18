package ee.mihkel.salat.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

// {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}
@Getter
@Entity // <--- tekib andmebaasi selle classi nimetusega tabel
@NoArgsConstructor
public class Toiduaine {
    @Id
    private String nimetus; // primaarvõti ehk selle abil kustutame ja muudame
    private double valgud; // veerg nr2
    private double rasvad; // veerg nr3
    private double sysivesikud; // veerg nr4

    public Toiduaine(String nimetus, double valgud, double rasvad, double sysivesikud) throws Exception {
        if (valgud + rasvad + sysivesikud > 100) {
            throw new Exception("Protsent kokku ei saa ületada 100!");
        }
        this.nimetus = nimetus;
        this.valgud = valgud;
        this.rasvad = rasvad;
        this.sysivesikud = sysivesikud;
    }
}
