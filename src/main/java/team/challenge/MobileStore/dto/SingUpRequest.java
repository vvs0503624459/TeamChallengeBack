package team.challenge.MobileStore.dto;

import javax.annotation.RegEx;

public record SingUpRequest(
        String firstname,
        String lastname,
        String phoneNumber,
        String email,
        String password

) {
}
