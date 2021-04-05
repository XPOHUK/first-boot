package edu.jm.firstboot.service;


import edu.jm.firstboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}
