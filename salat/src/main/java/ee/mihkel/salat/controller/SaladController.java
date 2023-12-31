package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.entity.Toidukomponent;
import ee.mihkel.salat.entity.Toit;
import ee.mihkel.salat.repository.ToiduaineRepository;
import ee.mihkel.salat.repository.ToidukomponentRepository;
import ee.mihkel.salat.repository.ToitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// StudyController     PersonController   RoomController
@RestController
public class SaladController {

    @Autowired
     ToiduaineRepository toiduaineRepository;

    @Autowired
    ToidukomponentRepository toidukomponentRepository;

    @Autowired
    ToitRepository toitRepository;

    // LISTI ASEMEL ANDMEBAAS
//    List<Toiduaine> toiduained = new ArrayList<>(); // [] <------------------

    // localhost:8080/lisa-toiduaine/kartul/4/2/12
    // @GetMapping("lisa-toiduaine/{nimetus}/{valk}/{rasv}/{sysivesik}")
    // localhost:8080/lisa-toiduaine?nimetus=kartul&valgud=4&rasvad=2&sysivesikud=12
    @GetMapping("lisa-toiduaine") // localhost:8080/lisa-toiduaine
    public List<Toiduaine> lisaToiduaine(
            @RequestParam String nimetus,
            @RequestParam double valgud,
            @RequestParam double rasvad,
            @RequestParam double sysivesikud
    ) throws Exception {
        Toiduaine toiduaine = new Toiduaine(nimetus, valgud, rasvad, sysivesikud);
//        Toiduaine toiduaine = Toiduaine.builder()
//                .nimetus(nimetus)
//                .valgud(valgud)
//                .build();
//        toiduaine.setNimetus(nimetus);
//        toiduaine.setValgud(valgud);
//        toiduaine.setRasvad(rasvad);
//        toiduaine.setSysivesikud(sysivesikud);
//        toiduained.add(toiduaine);
        toiduaineRepository.save(toiduaine);
        System.out.println("Toiduaine lisatud!");
        return toiduaineRepository.findAll();
        // {message: "", code: 123}
    }
    // IntelliJ taaskäivitada
    // Lisage juurde ja vaadake kas ta tuleb andmebaasi
    // tehke parem klõps -> refresh andmebaasis
    // vajutage andmebaasile ja ruudustikule

//    List<Toidukomponent> toidukomponendid = new ArrayList<>(); <--------------------------------------

    // localhost:8080/lisa-toidukomponent?toiduaineNimetus=kartul&kogus=200
    @PostMapping("lisa-toidukomponent")
    public List<Toidukomponent> lisaToidukomponent(
            @RequestParam String toiduaineNimetus,
            @RequestParam int kogus
    ) throws Exception {
        // lähen otsin toiduaineNimetuse alusel toiduainete Listist õige üles
//        Toiduaine toiduaine = null;
//        for (Toiduaine t: toiduaineRepository.findAll()) {
//            if (t.getNimetus().equals(toiduaineNimetus)) {
//                toiduaine = t;
//                break;
//            }
//        }
//        if (toiduaine == null) {
//            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
//        }
        Toiduaine toiduaine = toiduaineRepository.findById(toiduaineNimetus).get();
                                                    // TODO: muuta id
        Toidukomponent toidukomponent = new Toidukomponent(1, toiduaine, kogus);
        toidukomponentRepository.save(toidukomponent);
        return toidukomponentRepository.findAll();
    }

    // localhost:8080/saa-toidukomponendi-rasvad1/1
    @GetMapping("saa-toidukomponendi-rasvad1/{id}")
    public double saaToidukomponendiRasvad1(@PathVariable int id) throws Exception {
//        Toidukomponent toidukomponent = null;
//        for (Toidukomponent t: toidukomponendid) {
//            if (t.getId() == id) {
//                toidukomponent = t;
//                break;
//            }
//        }
//        if (toidukomponent == null) {
//            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
//        }
        Toidukomponent toidukomponent = toidukomponentRepository.findById(id).get();
        return toidukomponent.getKogus() * toidukomponent.getToiduaine().getRasvad() / 100;
    }

    // localhost:8080/saa-toidukomponendi-rasvad2/1
    @GetMapping("saa-toidukomponendi-rasvad2")
    public double saaToidukomponendiRasvad2(
            @RequestParam String toiduaineNimetus,
            @RequestParam int kogus
    ) throws Exception {
//        Toiduaine toiduaine = null;
//        for (Toiduaine t: toiduained) {
//            if (t.getNimetus().equals(toiduaineNimetus)) {
//                toiduaine = t;
//                break;
//            }
//        }
//        if (toiduaine == null) {
//            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
//        }
        Toiduaine toiduaine = toiduaineRepository.findById(toiduaineNimetus).get();
        return kogus * toiduaine.getRasvad() / 100;
    }

//    List<Toit> toidud = new ArrayList<>(); // <------------------------

    // localhost:8080/lisa-toit?nimetus=Kartulisalat&toidukomponentideIds=1,2
    @PostMapping("lisa-toit")
    public List<Toit> lisaToit(
            @RequestParam String nimetus,
            @RequestParam Integer[] toidukomponentideIds
    ) {
//        List<Toidukomponent> toiduosad = new ArrayList<>();
//
//        for (int id: toidukomponentideIds) {
//            for (Toidukomponent t: toidukomponendid) {
//                if (id == t.getId()) {
//                    toiduosad.add(t);
//                }
//            }
//        }
        List<Toidukomponent> toiduosad = toidukomponentRepository.findAllById(List.of(toidukomponentideIds));
        Toit toit = new Toit(nimetus,toiduosad);
//        toidud.add(toit);
        toitRepository.save(toit);
        return toitRepository.findAll();
    }

    // localhost:8080/saa-toidu-valgud?nimetus=Kartulisalat
    @GetMapping("saa-toidu-valgud")
    public double saaToiduValgud(
            @RequestParam String nimetus
    ) throws Exception {
//        Toit toit = null;
//        for (Toit t: toidud) {
//            if (t.getNimetus().equals(nimetus)) {
//                toit = t;
//                break;
//            }
//        }
//        if (toit == null) {
//            throw new Exception("Sellise nimetusega toitu ei eksisteeri!");
//        }
        Toit toit = toitRepository.findById(nimetus).get();
        return toit.saaValgud();
    }

//    @GetMapping("saa-toiduaine")
//    public Toiduaine saaToiduaine(@RequestParam String nimetus) {
//        return
//    }

    //    @GetMapping("saa-toidud-rasva-abil")
//    public List<Toit> saaToidud(@RequestParam double alg, @RequestParam double ylem) {
//        return
//    }
}

// GET - võtmiseks
// POST - lisamiseks
// DELETE - kustutamiseks
// PUT - muutmiseks
// PATCH - ühe kindla välja muutmiseks (nt broneering muuta reserveerituks, tellimus makstuks,
                //      kogus muudetakse ühe võrra väiksemaks)

// 18.10 Andmebaas, MUUDAME Salat projektis kõik andmebaasi minevateks
// 20.10 Loomad   UUS andmebaasiga seotud projekt
// 25.10 Proovitöö minupoolne lahendus
// 27.10 Proovitöö MUUDAME osas hakkame andmebaasi panema
