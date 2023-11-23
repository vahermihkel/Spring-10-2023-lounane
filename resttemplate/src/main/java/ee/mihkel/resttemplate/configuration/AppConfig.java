package ee.mihkel.resttemplate.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfig {

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate(); // restTemplate.exchange()
//    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setMessageConverters(new ArrayList<>());
//        return restTemplate; // restTemplate.exchange()
//    }
//
//    @Bean
//    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
//        return builder.setConnectTimeout(Duration.ofDays(1)).build(); // restTemplate.exchange()
//    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build(); // restTemplate.exchange()
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in); // scanner.nextLine()
    }

    @Bean
    public Random getRandom() {
        return new Random(); // random.nextInt()
    }

    // new Date();
    // new Product();
}
