package ee.mihkel.materials.controller;


import ee.mihkel.materials.entity.Kommentaar;
import ee.mihkel.materials.repository.KommentaarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class KommentaarController {

    @Autowired
    KommentaarRepository kommentaarRepository;

    @GetMapping("kommentaarid")
    public List<Kommentaar> saaKommentaarid() {
        return kommentaarRepository.findAll();
    }

    @PostMapping("kommentaarid")
    public List<Kommentaar> lisaKommentaar(@RequestBody Kommentaar kommentaar) {
        kommentaarRepository.save(kommentaar);
        return kommentaarRepository.findAll();
    }

    @DeleteMapping("kommentaarid/{id}")
    public List<Kommentaar> kustutaKommentaar(@PathVariable Long id) {
        kommentaarRepository.deleteById(id);
        return kommentaarRepository.findAll();
    }

    @PutMapping("kommentaarid")
    public List<Kommentaar> muudaKommentaar(@RequestBody Kommentaar Kommentaar) {
        kommentaarRepository.save(Kommentaar);
        return kommentaarRepository.findAll();
    }

    @GetMapping("kommentaarid/{id}")
    public Kommentaar saaKommentaar(@PathVariable Long id) {
        return kommentaarRepository.findById(id).orElseThrow();
    }
}
