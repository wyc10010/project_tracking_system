//package ntut.csie.sslab.account.application.springboot.web.security;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class JwtRequestFilterTest {
//
//    @Test
//    public void get_user_id_from_uri_should_success() {
//        String uri = "http://localhost:8081/ezkanban/account/users/8a90cf07-8b08-4db6-b220-37dc517e20b9/nickname";
//        assertEquals("8a90cf07-8b08-4db6-b220-37dc517e20b9", JwtRequestFilter.getUserIdFromUri(uri));
//    }
//
//    @Test
//    public void get_user_id_from_incorrect_uri_should_get_empty_string() {
//        String uri = "http://localhost:8080/ezkanban/team/teams?userId=18962d2f-fa95-4ad1-8440-06d3cccbcc61";
//        assertEquals("", JwtRequestFilter.getUserIdFromUri(uri));
//    }
//
//    @Test
//    public void get_jwt_from_authorization_header_should_success() {
//        String header = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDE5OTE1MTgsInVzZXJJZCI6IjhhOTBjZjA3LThiMDgtNGRiNi1iMjIwLTM3ZGM1MTdlMjBiOSIsImlhdCI6MTYwMTk1NTUxOH0.kT_hQmSt1YvFuuw4Vrz765dZ7I69cUDwzm-D0yIjb9c";
//        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDE5OTE1MTgsInVzZXJJZCI6IjhhOTBjZjA3LThiMDgtNGRiNi1iMjIwLTM3ZGM1MTdlMjBiOSIsImlhdCI6MTYwMTk1NTUxOH0.kT_hQmSt1YvFuuw4Vrz765dZ7I69cUDwzm-D0yIjb9c",
//                        JwtRequestFilter.getJwtFromAuthorizationHeader(header));
//    }
//}
