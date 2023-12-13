package team.challenge.MobileStore.model;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    @Email
    private String username;
    private String password;
    private Set<RoleModel> roleModels;
    private AuthProvider provider;

}
