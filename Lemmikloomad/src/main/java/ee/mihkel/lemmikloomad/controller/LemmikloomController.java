package ee.mihkel.lemmikloomad.controller;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import ee.mihkel.lemmikloomad.entity.Omanik;
import ee.mihkel.lemmikloomad.model.OmanikDTO;
import ee.mihkel.lemmikloomad.repository.LemmikloomRepository;
import ee.mihkel.lemmikloomad.repository.OmanikRepository;
import ee.mihkel.lemmikloomad.service.LemmikloomService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    // localhost:8080/lisa-loom?nimetus=siga&kaal=25.5&omanikuNimi=Kalle&omanikuTel=55123333&omanikuEmail=Kalle@gmail.com&omanikuAadress=Tammsaare111&omanikuIsikukood=388112233445
    @PostMapping("lisa-loom")
    public ResponseEntity<List<Lemmikloom>> lisaLoom(
            @RequestParam String nimetus,
            @RequestParam double kaal,
            @RequestParam String omanikuNimi,
            @RequestParam String omanikuTel,
            @RequestParam String omanikuEmail,
            @RequestParam String omanikuAadress,
            @RequestParam String omanikuIsikukood){
                            //   new LemmikLoom(nimetus, kaal)
        Lemmikloom lemmikloom = Lemmikloom.builder()
                .nimetus(nimetus)
                .kaal(kaal)
                .build();
        lemmikloomRepository.save(lemmikloom);

        Optional<Omanik> omanikOptional = omanikRepository.findById(omanikuNimi);
//        Omanik omanik = loomaLisamine.getOmanik();
        Omanik omanik = omanikOptional.orElseGet(() -> Omanik.builder()
                .nimi(omanikuNimi)
                .tel(omanikuTel)
                .email(omanikuEmail)
                .aadress(omanikuAadress)
                .isikukood(omanikuIsikukood)
                .lemmikloomad(new ArrayList<>())
                .build());
        omanik.getLemmikloomad().add(lemmikloom);
        omanikRepository.save(omanik);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

//        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(lemmikloomRepository.findAll()); // 201
        return new ResponseEntity<>(lemmikloomRepository.findAll(),headers,HttpStatus.CREATED);
    }
    // 1. Staatuskoodi võimalik muuta
    // 2. Seadistada ehk headereid kaasa anda

    @GetMapping("loomad") // localhost:8080/loomad?page=1&size=2
    public ResponseEntity<Page<Lemmikloom>> saaLoomad(Pageable pageable) {
//        Pageable pageable1 = new PageRequest(pageable.getPageNumber(), 10);
        Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), 3);
        return ResponseEntity.ok(lemmikloomRepository.findAllBy(pageable1));
    }

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("omanikud") // localhost:8080/omanikud
    public ResponseEntity<List<OmanikDTO>> saaOmanikud() {
//        ModelMapper modelMapper = new ModelMapper();
//        List<OmanikDTO> omanikDTOs = new ArrayList<>();
//        for (Omanik o: omanikRepository.findAll()) {
//            OmanikDTO omanikDTO = new OmanikDTO();
//            omanikDTO.setNimi(o.getNimi());
//            omanikDTO.setEmail(o.getEmail());
//            omanikDTOs.add(omanikDTO);
//        }
        List<OmanikDTO> omanikDtos = omanikRepository.findAll()
                .stream()
                .map(omanik -> modelMapper.map(omanik, OmanikDTO.class))
                .toList();
        return ResponseEntity.ok(omanikDtos);
    }

    @GetMapping("loomade-arv")
    public ResponseEntity<Integer> saaLoomadeArv(@RequestParam String omanikuNimi) {
        Omanik omanik = omanikRepository.findById(omanikuNimi).get();
        return ResponseEntity.ok(omanik.getLemmikloomad().size());
    }

    // http://localhost:8080/omaniku-loomad?omanikuNimi=Mihkel
    @GetMapping("omaniku-loomad")
    public ResponseEntity<List<Lemmikloom>> saaOmanikuLoomad(@RequestParam String omanikuNimi) {
        Omanik omanik = omanikRepository.findById(omanikuNimi).get();
        return ResponseEntity.ok(omanik.getLemmikloomad());
    }

    // http://localhost:8080/väikseim-loom?omanikuNimi=Mihkel
    @GetMapping("väikseim-loom")
    public ResponseEntity<Lemmikloom> saaVaikseimLoom(@RequestParam String omanikuNimi) {
        return ResponseEntity.ok(lemmikloomService.getLemmikloom("väiksem", omanikuNimi));
    }

    // http://localhost:8080/suurim-loom?omanikuNimi=Mihkel
    @GetMapping("suurim-loom")
    public ResponseEntity<Lemmikloom> saaSuurimLoom(@RequestParam String omanikuNimi) {
        return ResponseEntity.ok(lemmikloomService.getLemmikloom("suurem", omanikuNimi));
    }

    @GetMapping("suurim-loom-yldse")
    public ResponseEntity<Lemmikloom> saaSuurimLoomYldse() {
//        List<Lemmikloom> lemmikloomad = lemmikloomRepository.findAll();
//        Lemmikloom leitudSuurimLemmikloom = lemmikloomad.get(0);
//        for (Lemmikloom l: lemmikloomad) {
//            if (leitudSuurimLemmikloom.getKaal() < l.getKaal()) {
//                leitudSuurimLemmikloom =  l;
//            }
//        }
        return ResponseEntity.ok(lemmikloomRepository.findFirstByOrderByKaalDesc());
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
