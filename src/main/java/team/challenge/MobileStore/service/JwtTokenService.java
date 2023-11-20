package team.challenge.MobileStore.service;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String generateToken(@NonNull final UserDetails userDetails);
    String validateTokenAndGetUsername(@NonNull final String token);
}
