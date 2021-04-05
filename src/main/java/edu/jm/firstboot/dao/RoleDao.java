package edu.jm.firstboot.dao;


import edu.jm.firstboot.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    void add(Role role);
    Role getRoleByName(String name);
}
