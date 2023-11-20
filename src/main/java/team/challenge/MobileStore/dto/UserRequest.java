package team.challenge.MobileStore.dto;

public record UserRequest(
        String email,
        String password,
        String firstname,
        String lastname,
        String picture,
        String gender
) {
}
