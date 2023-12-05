package team.challenge.MobileStore.dto;

/**
 * DTO with information about same devices.
 *
 * @param id - device ID.
 * @param color - device color.
 */
public record DeviceWithSameColorDto(
        String id,
        String color
) {
}