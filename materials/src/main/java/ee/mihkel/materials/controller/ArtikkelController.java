package ee.mihkel.materials.controller;

import ee.mihkel.materials.entity.Artikkel;
import ee.mihkel.materials.repository.ArtikkelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ArtikkelController {

    @Autowired
    ArtikkelRepository artikkelRepository;

    @GetMapping("artiklid")
    public List<Artikkel> saaArtiklid() {
        ///////////////////////
        return artikkelRepository.findAll();
    }

    @PostMapping("artiklid")
    public List<Artikkel> lisaArtikkel(@RequestBody Artikkel artikkel) {
        ///////////////////////
        artikkelRepository.save(artikkel);
        return artikkelRepository.findAll();
    }

    @DeleteMapping("artiklid/{id}")
    public List<Artikkel> kustutaArtikkel(@PathVariable Long id) {
        ///////////////////////
        artikkelRepository.deleteById(id);
        return artikkelRepository.findAll();
    }

    @PutMapping("artiklid")
    public List<Artikkel> muudaArtikkel(@RequestBody Artikkel artikkel) {
        ///////////////////////
        artikkelRepository.save(artikkel);
        return artikkelRepository.findAll();
    }

    @GetMapping("artiklid/{id}")
    public Artikkel saaArtikkel(@PathVariable Long id) {
        ///////////////////////
        return artikkelRepository.findById(id).orElseThrow();
    }
}
