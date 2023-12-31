package ShoppingProejct.shoppingmall.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * JWT 토큰 관리 서비스
 */
@Service
@Slf4j
public class JwtProvider {

    private final SecretKey SECRET_KEY;
    private final long ACCESSTOKEN_EXP_TIME = 1000 * 60 * 30;
    private final long REFRESHTOKEN_EXP_TIME = 1000 * 60 * 60 * 24 * 7;


    public JwtProvider(@Value("${spring.secret-key}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    // accesstoken 생성
    public String createAccessToken(String memberId) {
        if(ACCESSTOKEN_EXP_TIME <= 0){
            throw new RuntimeException("JWT 생성시 만료시간이 0보다 커야합니다.");
        }
        long now = (new Date()).getTime();
        Date accesstokenExp = new Date(now + ACCESSTOKEN_EXP_TIME);

        return Jwts.builder()
                .header().type("JWT").and()
                .issuer("shoppingProject")
                .subject("accessToekn")
                .expiration(accesstokenExp)
                .issuedAt(new Date())
                .claim("memberId", memberId)
                .signWith(SECRET_KEY)
                .compact();
    }

    // refreshtoken 생성
    public String createRefreshToken(String memberId) {
        if(REFRESHTOKEN_EXP_TIME <= 0){
            throw new RuntimeException("JWT 생성시 만료시간이 0보다 커야합니다.");
        }
        long now = (new Date()).getTime();

        Date refreshTokenExp = new Date(now + REFRESHTOKEN_EXP_TIME);

        return Jwts.builder()
                .header().type("JWT").and()
                .issuer("shoppingProject")
                .subject("refreshToken")
                .expiration(refreshTokenExp)
                .issuedAt(new Date())
                .claim("memberId", memberId)
                .signWith(SECRET_KEY)
                .compact();
    }

    public Boolean validationToken(String jwt){
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build()
                    .parseSignedClaims(jwt)
                    .getPayload().getIssuer().equals("shoppingProject");
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new JwtException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            /**
             * todo: accesstoken 만료시 refreshtoken 요구
             */
            log.info("만료된 JWT 토큰입니다.");
            throw new JwtException("jwt is expired");

        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new JwtException("지원되지 않는 JWT 토큰입니다.");

        } catch (IllegalArgumentException e) {
            log.info("유효하지 않은 JWT 토큰입니다.");
            throw new JwtException("유효하지 않은 JWT 토큰입니다.");
        }
    }

    /*
        jwt의 회원 번호 조회
     */
    public int getMemberId(String jwt) {
        // payload, decoder 생성
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = jwt.split("\\.")[1];

        // memberId 조회
        payload = new String(decoder.decode(payload));
        JsonParser jsonParser = new BasicJsonParser();
        Map<String, Object> payloadArray = jsonParser.parseMap(payload);

        return Integer.parseInt((String) payloadArray.get("memberId"));
    }

}
