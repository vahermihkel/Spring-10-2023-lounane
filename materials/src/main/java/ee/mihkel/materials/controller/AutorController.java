package ee.mihkel.materials.controller;


import ee.mihkel.materials.entity.Autor;
import ee.mihkel.materials.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping("autorid")
    public List<Autor> saaAutorid() {
        return autorRepository.findAll();
    }

    @PostMapping("autorid")
    public List<Autor> lisaAutor(@RequestBody Autor autor) {
        autorRepository.save(autor);
        return autorRepository.findAll();
    }

    @DeleteMapping("autorid/{id}")
    public List<Autor> kustutaAutor(@PathVariable String id) {
        autorRepository.deleteById(id);
        return autorRepository.findAll();
    }

    @PutMapping("autorid")
    public List<Autor> muudaAutor(@RequestBody Autor autor) {
        autorRepository.save(autor);
        return autorRepository.findAll();
    }

    @GetMapping("autorid/{id}")
    public Autor saaAutor(@PathVariable String id) {
        return autorRepository.findById(id).orElseThrow();
    }
}
