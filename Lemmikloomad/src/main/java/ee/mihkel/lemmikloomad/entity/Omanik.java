package ee.mihkel.lemmikloomad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Omanik {
    @Id
    private String nimi;

    private String tel;
    private String email;
    private String isikukood;
    private String aadress;

    @OneToMany
    private List<Lemmikloom> lemmikloomad;
}
