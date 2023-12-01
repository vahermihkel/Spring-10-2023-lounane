package ee.mihkel.lemmikloomad.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class JwtFilter extends BasicAuthenticationFilter {

    // The dependencies of some of the beans in the application context form a cycle:
    public JwtFilter(@Lazy AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.error(request.getRequestURI());
//        log.error(request.getHeader("Nimi"));
        String authorizationHeader = request.getHeader("Authorization");
        log.info(authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            log.info("Token: {}", token);

//            Jwts.parser()
//                    .setSigningKey()
//                    .build()
//                    .parse()
//                    .;
        }

        super.doFilterInternal(request, response, chain);
    }
}
