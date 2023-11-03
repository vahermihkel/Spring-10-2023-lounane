package ee.mihkel.materials.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Kommentaar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date kuupaev;
    private String sisu;

    @ManyToOne
    private Artikkel artikkel;
}

// Artikkel ---> hunnik kommentaare
// Kommentaaril   <--- hunnik artikleid EI

// Kui ühel artiklil on 1 kommentaar
// @OneToOne , nii kui lisan artiklile teise kommentaari, siis on katki
// ütleb, et artiklil on juba kommentaar olemas

// @ManyToOne ütleb, et artiklil võib olla mitu kommentaari

// OneToOne
// Isik ---> 1 profiil
// Profiilil <--- hunnik isikuid EI
