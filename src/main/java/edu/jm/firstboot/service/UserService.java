package edu.jm.firstboot.service;


import edu.jm.firstboot.model.User;
import edu.jm.firstboot.model.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    void createUser(UserDto user);
    List<User> listUsers();
    void removeUser(User user);
    void updateUser(User user);
    User getUserById(long id);
    Map<String, String> validateUser(UserDto user);
}
