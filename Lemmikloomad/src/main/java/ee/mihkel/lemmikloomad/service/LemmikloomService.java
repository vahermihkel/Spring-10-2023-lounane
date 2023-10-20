package ee.mihkel.lemmikloomad.service;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import ee.mihkel.lemmikloomad.entity.Omanik;
import ee.mihkel.lemmikloomad.repository.OmanikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service // on loodud new LemmikloomService()
public class LemmikloomService {
    @Autowired
    OmanikRepository omanikRepository;

    public Lemmikloom getLemmikloom(String kumbaPidi, String omanikuNimi) {
        Omanik omanik = omanikRepository.findById(omanikuNimi).get();
        List<Lemmikloom> lemmikloomad = omanik.getLemmikloomad();
        Lemmikloom lemmikloom = omanik.getLemmikloomad().get(0);
        if (kumbaPidi.equals("suurem")) {
            for (Lemmikloom l: lemmikloomad) {
                if (lemmikloom.getKaal() < l.getKaal()) {
                    lemmikloom =  l;
                }
            }
        } else {
            for (Lemmikloom l: lemmikloomad) {
                if (lemmikloom.getKaal() > l.getKaal()) {
                    lemmikloom =  l;
                }
            }
        }
//        Lemmikloom lemmikloom = lemmikloomad.stream()
//                .filter(l -> kumbaPidi.equals("suurem") ? (lemmikloom.getKaal() < l.getKaal()) : (lemmikloom.getKaal() > l.getKaal()))
//                .max(Comparator.comparing(Lemmikloom::getKaal))
//                .orElse(lemmikloom);

        return lemmikloom;
//        Omanik omanik = omanikRepository.findById(omanikuNimi).orElseThrow(() -> new RuntimeException("Omanik not found"));
//        List<Lemmikloom> lemmikloomad = omanik.getLemmikloomad();
//
//        Comparator<Lemmikloom> comparator = (kumbaPidi.equals("suurem"))
//                ? Comparator.comparing(Lemmikloom::getKaal)
//                : Comparator.comparing(Lemmikloom::getKaal).reversed();
//
//        return lemmikloomad.stream()
//                .max(comparator)
//                .orElseThrow(() -> new RuntimeException("No Lemmikloom found"));
    }
}
