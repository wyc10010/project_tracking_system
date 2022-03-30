//package ntut.csie.sslab.account.application.springboot.web.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class JwtServiceTest {
//    private String userId;
//    private JwtService jwtService;
//    private final String SECRET_KEY = "secret";
//
//    @BeforeEach
//    void setUp() {
//        jwtService = new JwtService(SECRET_KEY);
//        userId = "1";
//    }
//
//    @Test
//    public void should_success_when_generate_a_jwt_token() {
//        String token = jwtService.generateToken(
//                        userId,
//                        Role.User.toString(),
//                        now(),
//                        tenHoursLater(),
//                        SignatureAlgorithm.HS256);
//
//        assertNotNull(token);
//        assertEquals(userId, jwtService.extractUserId(token));
//        List<String> roles = jwtService.extractAuthorities(token);
//        assertEquals(Role.User.toString(), roles.get(0));
//    }
//
//    @Test
//    public void should_success_when_validate_a_valid_jwt_token() {
//        String token = jwtService.generateToken(
//                        userId,
//                        Role.User.toString(),
//                        now(),
//                        tenHoursLater(),
//                        SignatureAlgorithm.HS256);
//
//        assertTrue(jwtService.isUserIdEqualToToken(token, userId));
//    }
//
//    @Test
//    public void should_fail_when_validate_a_valid_jwt_token_with_incorrect_user_id() {
//        String incorrectUserId = "incorrectUserId";
//        String token = jwtService.generateToken(
//                            incorrectUserId,
//                            Role.User.toString(),
//                            now(),
//                            tenHoursLater(),
//                            SignatureAlgorithm.HS256);
//
//        assertTrue(jwtService.isUserIdEqualToToken(token, incorrectUserId));
//    }
//
//    @Test
//    public void should_throw_SignatureException_when_validate_jwt_token_with_an_incorrect_secret_key() {
//        String SECRET_KEY = "incorrect_secret";
//        String token = jwtService.generateToken(
//                userId,
//                Role.User.toString(),
//                now(),
//                tenHoursLater(),
//                SignatureAlgorithm.HS256);
//
//        jwtService = new JwtService(SECRET_KEY);
//
//        assertThrows(SignatureException.class, () -> {
//            jwtService.isUserIdEqualToToken(token, userId);
//        });
//    }
//
//    @Test
//    public void should_throw_ExpiredJwtException_when_validate_an_expired_jwt_token() {
//        String token = jwtService.generateToken(
//                userId,
//                Role.User.toString(),
//                now(),
//                new Date(System.currentTimeMillis() - 1),
//                SignatureAlgorithm.HS256);
//
//        assertThrows(ExpiredJwtException.class, () -> {
//            jwtService.isUserIdEqualToToken(token, userId);
//        });
//    }
//
//    private Date now() {
//        return new Date(System.currentTimeMillis());
//    }
//
//    private Date tenHoursLater() {
//        return new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
//    }
//
//}
