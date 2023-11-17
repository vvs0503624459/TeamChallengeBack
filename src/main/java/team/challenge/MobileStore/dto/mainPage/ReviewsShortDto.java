package team.challenge.MobileStore.dto.mainPage;

/**
 * DTO with information about device rating.
 *
 * @param rating - mark.
 * @param countOfVotes - count of votes.
 */
public record ReviewsShortDto(
        Double rating,
        Integer countOfVotes
) {
}
