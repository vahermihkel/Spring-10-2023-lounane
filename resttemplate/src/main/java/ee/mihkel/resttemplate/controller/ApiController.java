package ee.mihkel.resttemplate.controller;

import ee.mihkel.resttemplate.model.Nordpool;
import ee.mihkel.resttemplate.model.Omniva;
import ee.mihkel.resttemplate.model.Post;
import ee.mihkel.resttemplate.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @GetMapping("omniva")
    public List<Omniva> getOmnivaParcelMachines() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.omniva.ee/locations.json";
//        Optional<String> stringOptional = Optional.of("ss");
//        stringOptional.get();
        ResponseEntity<Omniva[]> response = restTemplate.exchange(url, HttpMethod.GET,null, Omniva[].class);
//        System.out.println(response.getBody());
    // tekitame mudeli
        List<Omniva> omniva = Arrays.stream(response.getBody())
                .filter(e -> e.getA0_NAME().equals("EE"))
                .toList();

        return omniva;
    }

    @GetMapping("posts/{userId}")
    public List<Post> getPosts(@PathVariable int userId) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.org/posts";
        ResponseEntity<Post[]> response = restTemplate.exchange(url, HttpMethod.GET,null, Post[].class);
//        System.out.println(response.getBody());
        // tekitame mudeli
        List<Post> posts = Arrays.stream(response.getBody())
                .filter(e -> e.getUserId() == userId)
//                .map(e -> new PostShortened(e.getContent(), e.getTitle()))
                .toList();

        return posts;
    }

    @GetMapping("products/{minRating}")
    public List<Product> getProducts(@PathVariable double minRating) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<Product[]> response = restTemplate.exchange(url, HttpMethod.GET,null, Product[].class);

        List<Product> products = Arrays.stream(response.getBody())
                .filter(e -> e.getRating().getRate() > minRating)
                .toList();

        return products;
    }

    @GetMapping("nordpool") // country
    public Nordpool getNordpool() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dashboard.elering.ee/api/nps/price";
        ResponseEntity<Nordpool> response = restTemplate.exchange(url, HttpMethod.GET,null, Nordpool.class);

//        List<Product> products = Arrays.stream(response.getBody())
//                .filter(e -> e. )
//                .toList();

        return response.getBody();
    }
}
