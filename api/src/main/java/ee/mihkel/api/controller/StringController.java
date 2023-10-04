package ee.mihkel.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class StringController { // klassi loogeline sulg

    // localhost:8080 on default aadress, kus Spring käima läheb
    // GetMapping sees saan öelda mis peab järgnema sellele aadressile, et funktsioon käivituks
    // localhost:8080/hi ---> tagastatakse eesrakendusele returnile järgnev väärtus
    @GetMapping("hi")
    public String helloWorld() {
        return "Hello world at " + new Date();
    }

    @GetMapping("hi/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("nr/{number}")
    public String helloNumber(@PathVariable Integer number) {
        return "Hello " + number;
    }

    @GetMapping("multiply/{nr1}/{nr2}") // localhost:8080/multiply/3/4
    public Integer multiply(@PathVariable Integer nr1, @PathVariable Integer nr2) {
        return nr1 * nr2;
    }

    @GetMapping("multiply2") // localhost:8080/multiply2?nr1=3&nr2=4
    public Integer multiply2(@RequestParam Integer nr1, @RequestParam Integer nr2) {
        return nr1 * nr2;
    }

} // klassi loogeline sulg

// 400 - üldine viga, kasutajapoole ehk front-endi viga
// 404 - vale API otspunkt

// 500 - ambigous
