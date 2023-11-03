package ee.mihkel.materials.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Autor {
    @Id
    private String isikukood;

    private String eesnimi;

    private String perenimi;

    @OneToOne
    private KontaktAndmed kontaktAndmed;

}
