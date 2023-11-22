package team.challenge.MobileStore.model;

import lombok.Data;

import java.util.List;

@Data
public class SpecificationGroup {
    String title;
    List<Specification> specifications;
}
