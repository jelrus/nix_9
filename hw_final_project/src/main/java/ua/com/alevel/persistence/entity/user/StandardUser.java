package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.entity.user.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STANDARD_USER")
public class StandardUser extends User{

    public StandardUser() {
        super();
        setRoleType(RoleType.ROLE_STANDARD_USER);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
