package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.entity.Toidukomponent;
import ee.mihkel.salat.entity.Toit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// StudyController     PersonController   RoomController
@RestController
public class SaladController {
    List<Toiduaine> toiduained = new ArrayList<>(); // []

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
        toiduained.add(toiduaine);
        System.out.println("Toiduaine lisatud!");
        return toiduained;
        // {message: "", code: 123}
    }

    List<Toidukomponent> toidukomponendid = new ArrayList<>();

    // localhost:8080/lisa-toidukomponent?toiduaineNimetus=kartul&kogus=200
    @GetMapping("lisa-toidukomponent")
    public List<Toidukomponent> lisaToidukomponent(
            @RequestParam String toiduaineNimetus,
            @RequestParam int kogus
    ) throws Exception {
        // lähen otsin toiduaineNimetuse alusel toiduainete Listist õige üles
        Toiduaine toiduaine = null;
        for (Toiduaine t: toiduained) {
            if (t.getNimetus().equals(toiduaineNimetus)) {
                toiduaine = t;
                break;
            }
        }
        if (toiduaine == null) {
            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
        }
        Toidukomponent toidukomponent = new Toidukomponent(toidukomponendid.size()+1, toiduaine, kogus);
        toidukomponendid.add(toidukomponent);
        return toidukomponendid;
    }

    // localhost:8080/saa-toidukomponendi-rasvad1/1
    @GetMapping("saa-toidukomponendi-rasvad1/{id}")
    public double saaToidukomponendiRasvad1(@PathVariable int id) throws Exception {
        Toidukomponent toidukomponent = null;
        for (Toidukomponent t: toidukomponendid) {
            if (t.getId() == id) {
                toidukomponent = t;
                break;
            }
        }
        if (toidukomponent == null) {
            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
        }
        return toidukomponent.getKogus() * toidukomponent.getToiduaine().getRasvad() / 100;
    }

    // localhost:8080/saa-toidukomponendi-rasvad2/1
    @GetMapping("saa-toidukomponendi-rasvad2")
    public double saaToidukomponendiRasvad2(
            @RequestParam String toiduaineNimetus,
            @RequestParam int kogus
    ) throws Exception {
        Toiduaine toiduaine = null;
        for (Toiduaine t: toiduained) {
            if (t.getNimetus().equals(toiduaineNimetus)) {
                toiduaine = t;
                break;
            }
        }
        if (toiduaine == null) {
            throw new Exception("Sellise nimetusega toiduainet ei eksisteeri!");
        }
        return kogus * toiduaine.getRasvad() / 100;
    }

    List<Toit> toidud = new ArrayList<>();

    // localhost:8080/lisa-toit?nimetus=Kartulisalat&toidukomponentideIds=1,2
    @GetMapping("lisa-toit")
    public List<Toit> lisaToit(
            @RequestParam String nimetus,
            @RequestParam int[] toidukomponentideIds
    ) {
        List<Toidukomponent> toiduosad = new ArrayList<>();

        for (int id: toidukomponentideIds) {
            for (Toidukomponent t: toidukomponendid) {
                if (id == t.getId()) {
                    toiduosad.add(t);
                }
            }
        }

        Toit toit = new Toit(nimetus,toiduosad);
        toidud.add(toit);
        return toidud;
    }

    // localhost:8080/saa-toidu-valgud?nimetus=Kartulisalat
    @GetMapping("saa-toidu-valgud")
    public double saaToiduValgud(
            @RequestParam String nimetus
    ) throws Exception {
        Toit toit = null;
        for (Toit t: toidud) {
            if (t.getNimetus().equals(nimetus)) {
                toit = t;
                break;
            }
        }
        if (toit == null) {
            throw new Exception("Sellise nimetusega toitu ei eksisteeri!");
        }
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
