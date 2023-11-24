package ee.mihkel.lemmikloomad.model;

import ee.mihkel.lemmikloomad.entity.Omanik;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class LoomaLisamine {
    private String nimetus;
    private double kaal;
    private Omanik omanik;
//    private String omanikuNimi;
//    private String omanikuTel;
//    private String omanikuEmail;
//    private String omanikuAadress;
//    private String omanikuIsikukood;
}
