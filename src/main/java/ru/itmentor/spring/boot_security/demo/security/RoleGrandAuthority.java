package ru.itmentor.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import ru.itmentor.spring.boot_security.demo.entity.Role;

public class RoleGrandAuthority implements GrantedAuthority {

    private final Role role;

    public RoleGrandAuthority(Role role) {
        this.role = role;
    }

    /**
     * @return
     */
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
