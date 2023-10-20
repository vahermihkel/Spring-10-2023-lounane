package ee.mihkel.lemmikloomad.controller;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import ee.mihkel.lemmikloomad.entity.Omanik;
import ee.mihkel.lemmikloomad.repository.LemmikloomRepository;
import ee.mihkel.lemmikloomad.repository.OmanikRepository;
import ee.mihkel.lemmikloomad.service.LemmikloomService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LemmikloomController {

    @Autowired
    LemmikloomRepository lemmikloomRepository;

    @Autowired
    OmanikRepository omanikRepository;

    @Autowired
    LemmikloomService lemmikloomService;

    // localhost:8080/lisa-loom?nimetus=koer&kaal=2.3&omanikuNimi=Kaarel
    @PostMapping("lisa-loom")
    public List<Lemmikloom> lisaLoom(
            @RequestParam String nimetus,
            @RequestParam double kaal,
            @RequestParam String omanikuNimi) {
                            //   new LemmikLoom(nimetus, kaal)
        Lemmikloom lemmikloom = Lemmikloom.builder()
                .nimetus(nimetus)
                .kaal(kaal)
                .build();
        lemmikloomRepository.save(lemmikloom);

        Optional<Omanik> omanikOptional = omanikRepository.findById(omanikuNimi);
        Omanik omanik = omanikOptional.orElseGet(() -> Omanik.builder()
                .nimi(omanikuNimi)
                .lemmikloomad(new ArrayList<>())
                .build());
        omanik.getLemmikloomad().add(lemmikloom);
        omanikRepository.save(omanik);

        return lemmikloomRepository.findAll();
    }

    @GetMapping("loomade-arv")
    public int saaLoomadeArv(@RequestParam String omanikuNimi) {
        Omanik omanik = omanikRepository.findById(omanikuNimi).get();
        return omanik.getLemmikloomad().size();
    }

    // http://localhost:8080/omaniku-loomad?omanikuNimi=Mihkel
    @GetMapping("omaniku-loomad")
    public List<Lemmikloom> saaOmanikuLoomad(@RequestParam String omanikuNimi) {
        Omanik omanik = omanikRepository.findById(omanikuNimi).get();
        return omanik.getLemmikloomad();
    }

    // http://localhost:8080/väikseim-loom?omanikuNimi=Mihkel
    @GetMapping("väikseim-loom")
    public Lemmikloom saaVaikseimLoom(@RequestParam String omanikuNimi) {
        return lemmikloomService.getLemmikloom("väiksem", omanikuNimi);
    }

    // http://localhost:8080/suurim-loom?omanikuNimi=Mihkel
    @GetMapping("suurim-loom")
    public Lemmikloom saaSuurimLoom(@RequestParam String omanikuNimi) {
        return lemmikloomService.getLemmikloom("suurem", omanikuNimi);
    }

    @GetMapping("suurim-loom-yldse")
    public Lemmikloom saaSuurimLoomYldse() {
//        List<Lemmikloom> lemmikloomad = lemmikloomRepository.findAll();
//        Lemmikloom leitudSuurimLemmikloom = lemmikloomad.get(0);
//        for (Lemmikloom l: lemmikloomad) {
//            if (leitudSuurimLemmikloom.getKaal() < l.getKaal()) {
//                leitudSuurimLemmikloom =  l;
//            }
//        }
        return lemmikloomRepository.findFirstByOrderByKaalDesc();
    }

    // localhost:8080/saa-loomad-vahemikus?min=1000&max=1100
    @GetMapping("saa-loomad-vahemikus")
    public List<Lemmikloom> saaLoomadVahemikus(
            @RequestParam double min,
            @RequestParam double max
    ) {
        return lemmikloomRepository.findAllByKaalBetween(min, max);
    }

    // localhost:8080/lisa-omanik?nimi=Kaarel
//    @PostMapping("lisa-omanik")
//    public List<Omanik> lisaOmanik(@RequestParam String nimi) {
//                            //   new Omanik(nimi, new ArrayList())
//        Omanik omanik = Omanik.builder()
//                .nimi(nimi)
//                .build();
//        omanikRepository.save(omanik);
//        return omanikRepository.findAll();
//    }

    // localhost:8080/lisa-omanikule-lemmikloom?omanik=Kaarel&lemmikloom=1
//    @PostMapping("lisa-omanikule-lemmikloom")
//    public List<Omanik> lisaOmanikuleLoom(@RequestParam String omanik, @RequestParam Integer lemmikloom) {
//        Omanik leitudOmanik = omanikRepository.findById(omanik).get();
//        Lemmikloom leitudLemmikloom = lemmikloomRepository.findById(lemmikloom).get();
//        leitudOmanik.getLemmikloomad().add(leitudLemmikloom);
//
//        omanikRepository.save(leitudOmanik);
//        return omanikRepository.findAll();
//    }
}
