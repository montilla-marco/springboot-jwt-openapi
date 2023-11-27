package ms.mmontilla.registry.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtUtils {

    public static String getAccessToken(String userName) {
        Algorithm algorithm = Algorithm.HMAC256("secret-jwt".getBytes());
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}
