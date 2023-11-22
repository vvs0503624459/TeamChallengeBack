package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class RoleModel implements GrantedAuthority {
    @Id
    private String id;
    private String roleName;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName;
    }
}
