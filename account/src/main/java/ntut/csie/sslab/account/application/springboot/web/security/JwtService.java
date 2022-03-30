//package ntut.csie.sslab.account.application.springboot.web.security;
//
//import io.jsonwebtoken.*;
//import ntut.csie.sslab.account.users.command.usecase.TokenGenerator;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class JwtService implements TokenGenerator{
//
//    private final String secretKey;
//    private static final String KEY_USER_ID = "userId";
//    private static final String AUTHORITIES = "authorities";
//
//    public JwtService(String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    @Override
//    public String generateToken(String userId,
//                                String role,
//                                Date issuedAt,
//                                Date expiration,
//                                SignatureAlgorithm signatureAlgorithm) {
////        Map<String, Object> claims = new HashMap<>();
////        claims.put(KEY_USER_ID, userId);
//
//
////        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
////                .commaSeparatedStringToAuthorityList(Role.User.toString());
//
////                .claim("authorities",
////                        grantedAuthorities.stream()
////                                .map(GrantedAuthority::getAuthority)
////                                .collect(Collectors.toList()))
//
//
////        List<String> roles = new ArrayList<>();
////        roles.add(role);
////        claims.put(AUTHORITIES, grantedAuthorities.stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.toList()));
////
////        return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt)
////                .setExpiration(expiration)
////                .signWith(signatureAlgorithm, secretKey).compact();
//        return null;
//    }
//
//    public String extractUserId(String token){
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims.get(KEY_USER_ID, String.class);
//    }
//
//    public Boolean isUserIdEqualToToken(String token, String userId) throws ExpiredJwtException, SignatureException {
//        return extractUserId(token).equals(userId);
//    }
//
//    public List<String> extractAuthorities(String token){
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims.get(AUTHORITIES, List.class);
//
//    }
//
//    public Claims getClaims(String token){
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims;
//
//    }
//}
