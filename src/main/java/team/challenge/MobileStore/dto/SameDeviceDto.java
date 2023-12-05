package team.challenge.MobileStore.dto;

/**
 * DTO with information about same devices.
 *
 * @param id - device ID.
 * @param value - device same field value.
 */
public record SameDeviceDto(
        String id,
        String value
) {
}