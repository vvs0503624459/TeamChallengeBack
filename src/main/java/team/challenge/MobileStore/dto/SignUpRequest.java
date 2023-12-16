package team.challenge.MobileStore.dto;

public record SignUpRequest(
        String firstname,
        String lastname,
        String phoneNumber,
        String email,
        String password

) {
}
