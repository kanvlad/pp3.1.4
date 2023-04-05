package tech.itmentors.crud.security;

import org.springframework.security.core.GrantedAuthority;
import tech.itmentors.crud.model.Role;

public class RoleGrandAuthority implements GrantedAuthority {

    private final Role role;

    public RoleGrandAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
