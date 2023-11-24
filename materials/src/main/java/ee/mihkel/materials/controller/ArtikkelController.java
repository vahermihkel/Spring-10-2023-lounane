package ee.mihkel.materials.controller;

import ee.mihkel.materials.entity.Artikkel;
import ee.mihkel.materials.exception.SisuNotFoundException;
import ee.mihkel.materials.repository.ArtikkelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Artikkel> lisaArtikkel(@RequestBody Artikkel artikkel) throws RuntimeException {
        ///////////////////////
        if (artikkel.getPealkiri() == null || artikkel.getPealkiri().isEmpty()) {
            throw new RuntimeException("Pealkiri puudu");
        }
        artikkelRepository.save(artikkel);
        return artikkelRepository.findAll();
    }

    @DeleteMapping("artiklid/{id}")
    public List<Artikkel> kustutaArtikkel(@PathVariable Long id) throws NoSuchElementException {
        ///////////////////////
        if (artikkelRepository.existsById(id)) {
            artikkelRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Sellist ID-d ei eksisteeri");
        }
        return artikkelRepository.findAll();
    }

    @PutMapping("artiklid")
    public List<Artikkel> muudaArtikkel(@RequestBody Artikkel artikkel) throws SisuNotFoundException {
        ///////////////////////
        if (artikkel.getSisu() == null || artikkel.getSisu().isEmpty()) {
            throw new SisuNotFoundException("Pealkiri puudu");
        }
        artikkelRepository.save(artikkel);
        return artikkelRepository.findAll();
    }

    @GetMapping("artiklid/{id}")
    public Artikkel saaArtikkel(@PathVariable Long id) {
        ///////////////////////
        return artikkelRepository.findById(id).orElseThrow();
    }
}
