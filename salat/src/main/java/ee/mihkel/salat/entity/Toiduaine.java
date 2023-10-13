package ee.mihkel.salat.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

// {nimetus: "Kartul", valgud: 2.0, rasvad: 1.5, sysivesikud: 12.0}
@Getter
public class Toiduaine {
    private String nimetus;
    private double valgud;
    private double rasvad;
    private double sysivesikud;

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
