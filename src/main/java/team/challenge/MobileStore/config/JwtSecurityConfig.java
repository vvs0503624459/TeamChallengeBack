package team.challenge.MobileStore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * JWT security config - authorize user
 */
//@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class JwtSecurityConfig {
//    private final JwtRequestFilter jwtRequestFilter;
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
//        return http
//                .cors(withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/").permitAll()
//                        .anyRequest().hasAuthority("ROLE_USER"))
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
}
