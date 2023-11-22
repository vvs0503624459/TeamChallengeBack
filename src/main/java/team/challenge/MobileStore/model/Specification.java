package team.challenge.MobileStore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification {

    private String title;
    private String value;
    private String descriptionExtra;
    private boolean isMain;
}
