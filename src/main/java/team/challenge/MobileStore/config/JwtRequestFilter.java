package team.challenge.MobileStore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class filter to get jwt token from user.
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter  {
//    private final JwtTokenService jwtTokenService;
//    private final UserService userService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//
//            return;
//        }
//        final String token = header.substring(7);
//        final String username = jwtTokenService.validateTokenAndGetUsername(token);
//        if (username == null) {
//            // validation failed or token expired
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final UserDetails userDetails = userService.loadUserByUsername(username);
//        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // continue with authenticated user
//        filterChain.doFilter(request, response);
//    }
}
