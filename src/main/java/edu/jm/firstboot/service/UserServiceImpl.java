package edu.jm.firstboot.service;


import edu.jm.firstboot.dao.RoleDao;
import edu.jm.firstboot.dao.UserDao;
import edu.jm.firstboot.model.User;
import edu.jm.firstboot.model.UserDto;
import edu.jm.firstboot.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

   @Autowired
   BCryptPasswordEncoder passwordEncoder;

   @Autowired
   private UserDao userDao;

   @Autowired
   private RoleDao roleDao;

   @Override
   public void createUser(UserDto userDto) {
      if(validateUser(userDto).isEmpty()) {
         User user = new User(userDto);
         if (userDto.getRoles() == null ) {
            user.setRoles(new HashSet<>(Arrays.asList(roleDao.getRoleByName("ROLE_USER"))));
         } else {
            user.setRoles(userDto.getRoles());
         }
         user.setPassword(passwordEncoder.encode(userDto.getPassword()));
         user.enable();
         user.setAccountNonExpired(true);
         user.setIsAccountNonLocked(true);
         user.setIsCredentialsNonExpired(true);
         userDao.add(user);
      }
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void removeUser(User user) {
      userDao.removeUser(user);
   }

   @Override
   public void updateUser(User user) {
      userDao.updateUser(user);
   }

   @Override
   public User getUserById(long id){
      return userDao.getUserById(id);
   }

   @Override
   public Map<String, String> validateUser(UserDto userDto){
      Map<String,String> errorMap = new HashMap<>();
      if (userDto.getLoginName().isEmpty())
         errorMap.put("loginNameIsEmpty", "Login Name Is Empty!");
      else if (userDao.getUserByLoginName(userDto.getLoginName()) != null){
         errorMap.put("userExists", "An account for that username already exists.");
      }
      if (userDto.getFirstName().isEmpty())
         errorMap.put("firstNameIsEmpty", "First Name Is Empty!");
      if (userDto.getLastName().isEmpty())
         errorMap.put("lastNameIsEmpty", "Last Name Is Empty!");
      if (userDto.getPassword().isEmpty())
         errorMap.put("passwordIsEmpty", "Password Is Empty!");
      if (userDto.getEmail().isEmpty())
         errorMap.put("emailIsEmpty", "E-Mail Is Empty!");
      else if(!EmailValidator.validateEmail(userDto.getEmail()))
         errorMap.put("emailNotValid", "Email Not Valid!");
      return errorMap;
   }

}
