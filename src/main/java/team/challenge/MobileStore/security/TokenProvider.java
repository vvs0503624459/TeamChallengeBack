package team.challenge.MobileStore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.config.AppProperties;
import team.challenge.MobileStore.model.UserModel;

import java.util.Date;

@Service
public class TokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final AppProperties appProperties;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.hmac512 = Algorithm.HMAC512(this.appProperties.getAuth().getTokenSecret());
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String createToken(Authentication authentication){
        UserModel user = (UserModel) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
        return JWT.create()
                .withSubject(user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(expiryDate)
                .sign(this.hmac512);
    }

    public String validateTokenAndGetId(String token){
        try{
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException e){
            return null;
        }
    }
}
