package cinema.dao;

import cinema.model.Role;

public interface RoleDao {
    Role add(Role role);

    Role getByRoleName(Role.RoleName roleName);
}
