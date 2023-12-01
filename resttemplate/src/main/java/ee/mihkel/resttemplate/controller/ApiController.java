package ee.mihkel.resttemplate.controller;

import ee.mihkel.resttemplate.model.*;
import ee.mihkel.resttemplate.util.YldisedFunktsioonid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class ApiController {

    @Autowired
    YldisedFunktsioonid yldisedFunktsioonid;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Random random;

//    RestTemplate restTemplate = new RestTemplate();

    @Value("${everypay.url}")
    private String url;

    @Value("${everypay.username}")
    private String everypayUsername;

    @GetMapping("omniva") // localhost:8080/omniva
    public List<Omniva> getOmnivaParcelMachines() {
        yldisedFunktsioonid.kontrolliIsikukoodi();
        yldisedFunktsioonid.getRestTemplate();

//        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate);
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

    @GetMapping("posts/{userId}") // localhost:8080/posts/1
    public List<Post> getPosts(@PathVariable int userId) {
        yldisedFunktsioonid.getRestTemplate();

//        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate);
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

    @GetMapping("products/{minRating}") // localhost:8080/products/4.0
    public List<Product> getProducts(@PathVariable double minRating) {

//        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate);
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<Product[]> response = restTemplate.exchange(url, HttpMethod.GET,null, Product[].class);

        List<Product> products = Arrays.stream(response.getBody())
                .filter(e -> e.getRating().getRate() > minRating)
                .toList();

        return products;
    }

    @GetMapping("nordpool") // localhost:8080/nordpool
    public Nordpool getNordpool() {

//        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate);
        String url = "https://dashboard.elering.ee/api/nps/price";
        ResponseEntity<Nordpool> response = restTemplate.exchange(url, HttpMethod.GET,null, Nordpool.class);

//        List<Product> products = Arrays.stream(response.getBody())
//                .filter(e -> e. )
//                .toList();

        return response.getBody();
    }

    @GetMapping("payment") // localhost:8080/payment?amount=3000
    public String makePayment(
            @RequestParam double amount
    ) {

//        String url = url;

        EveryPayData body = new EveryPayData();
        body.setApi_username(everypayUsername);
        body.setAccount_name("EUR3D1");
        body.setAmount(amount); // hiljem teeme muudetavaks
        body.setOrder_reference(String.valueOf(random.nextInt(10000,999999))); // hiljem teeme muudetavaks
        body.setNonce("dadas" + ZonedDateTime.now() + random.nextInt(10000,999999)); // hiljem teeme muudetavaks
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setCustomer_url("https://maksmine.web.app");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("e36eb40f5ec87fa2", "7b91a3b9e1b74524c2e9fc282f8ac8cd");
//        headers.set(HttpHeaders.AUTHORIZATION, "TOKEN");

        HttpEntity<EveryPayData> entity = new HttpEntity<>(body, headers);

        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, EveryPayResponse.class);

        return response.getBody().getPayment_link();
    }
}
