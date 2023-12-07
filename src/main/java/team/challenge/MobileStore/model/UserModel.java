package team.challenge.MobileStore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel  {
    @Id
    private String id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String picture;
    private LocalDateTime lastVisit;
    private String gender;
    private List<Address> addresses;
    private Set<RoleModel> roleModels;

}
