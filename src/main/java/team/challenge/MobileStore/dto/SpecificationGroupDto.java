package team.challenge.MobileStore.dto;

import java.util.List;

public record SpecificationGroupDto(
        String title,
        List<SpecificationDto> specifications
) {
}
