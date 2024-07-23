package user_access.entities;

import ectimel.aggregates.EntityObject;
import ectimel.exceptions.NullException;
import user_access.value_objects.RoleName;
import user_access.value_objects.RoleId;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "roles")
public class Role extends EntityObject<RoleId> {
    
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false))
    private RoleName roleName;
    
    protected Role() {
        // for hibernate only
    }
    
    private Role(RoleId roleId, RoleName roleName) {
        super(roleId);
        if(roleName == null || roleId == null) throw new NullException("Role args can not be null.");
        this.roleName = roleName;
    }
    
    public static Role createUserRole() {
        RoleId roleId = RoleId.createUserId();
        RoleName roleName = RoleName.createRegularUserName();
        return new Role(roleId, roleName);
    }

    public static Role createAdminRole() {
        RoleId roleId = RoleId.createAdminId();
        RoleName roleName = RoleName.createAdminName();
        return new Role(roleId, roleName);
    }
}
