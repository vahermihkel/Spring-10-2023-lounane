package ee.mihkel.lemmikloomad.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

//    https://generate.plus/en/base64
    String key = "POoh+EiNPS/fQGk+dV6DLZGFx+O6KLAD7+4KQm6KgVH71Jy2KY9ZM7XZmAwUl09IVV7dMvKKrxP0GthEXADFlKGsG8t1fVguWR2bsLOpikBXyYihF0aHYuttELA+g9drZUHfdlEX3e/I2+d+9YLUHFHxVnQ6qkbsA7GoUbDk59o=";

    public String generateToken() {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)), SignatureAlgorithm.HS512)
                .setExpiration(new Date())
                .compact();
    }
}
