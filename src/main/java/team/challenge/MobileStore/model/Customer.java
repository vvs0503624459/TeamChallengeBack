package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private String name;
    private String fullName;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String mobilePhone;

}