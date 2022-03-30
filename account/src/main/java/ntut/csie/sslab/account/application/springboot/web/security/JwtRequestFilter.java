//package ntut.csie.sslab.account.application.springboot.web.security;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletMapping;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtService jwtService;
//
//    private static final Collection<String> excludeUrlPatterns = new HashSet<>(
//            Arrays.asList(
//                    "/**/users/register",
//                    "/**/users/login"
////                    "/**/users/**/managerunregister"
//            ));
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
////        if (SecurityContextHolder.getContext().getAuthentication() != null) {
////            chain.doFilter(request, response);
////            return;
////        }
////
////        String userIdFromUri = getUserIdFromUri(request.getRequestURI());
////        System.out.println(userIdFromUri);
////
////        String authorizationHeader = request.getHeader("Authorization");
////        String jwt = getJwtFromAuthorizationHeader(authorizationHeader);
////        String userIdFromJwt = jwtService.extractUserId(jwt);
////
////        UserDetails userDetails = this.userDetailsService.loadUserByUserId(userIdFromJwt);
////        List<SimpleGrantedAuthority> authority = (ArrayList) userDetails.getAuthorities();
////        String role = authority.get(0).toString();
////
////        if (userIdFromJwt.equals(userIdFromUri) || role.equals(Role.Manager.toString())){
////            storeValidatedAuthentication(request, userDetails);
////        }
////
////        chain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        return excludeUrlPatterns.stream()
//                .anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
//    }
//
//    private void storeValidatedAuthentication(HttpServletRequest request, UserDetails userDetails) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities());
//        usernamePasswordAuthenticationToken
//                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//    }
//
//    public static String getJwtFromAuthorizationHeader(String authorizationHeader){
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//
//        return "";
//    }
//
//    public static String getUserIdFromUri(String uri) {
//        String uriUserId = "";
//
//        Pattern pattern = Pattern.compile("users\\/(\\w{8}\\-(\\w{4}\\-){3}\\w{12})");
//
//        Matcher matcher = pattern.matcher(uri);
//
//        if(matcher.find())
//            uriUserId = matcher.group(1);
//        return uriUserId;
//    }
//
//
//}
