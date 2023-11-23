package ee.mihkel.resttemplate.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class YldisedFunktsioonid {

    public boolean kontrolliIsikukoodi() {
        return true;
    }

    public boolean kontrolliEmaili() {
        return true;
    }

    public boolean kontrolliTelefoninumbrit() {
        return true;
    }

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
