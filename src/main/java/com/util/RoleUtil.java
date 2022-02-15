package com.util;

import com.domain.Role;
import com.domain.RoleName;

public class RoleUtil {

    public static Role createRole() {
        Role role = Role.builder()
                .name(RoleName.ROLE_ADMIN)
                .build();

        return role;
    }
}
