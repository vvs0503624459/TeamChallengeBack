package team.challenge.MobileStore.dto;

/**
 * DTO with information about device rating.
 *
 * @param rating - mark.
 * @param countOfVotes - count of votes.
 */
public record ReviewMarkDto(
        Double rating,
        Integer countOfVotes
) {
}
