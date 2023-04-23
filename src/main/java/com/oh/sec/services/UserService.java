package com.oh.sec.services;

import com.oh.sec.dto.Login;
import com.oh.sec.models.Role;
import com.oh.sec.models.User;

import java.util.List;

public interface UserService {

    public void register(User user, String roleName);

    public User findByid(Integer id);

    public User login(Login login);

    public User save(User user);

    public User addRole(Integer id, Role role);
    public User delRole(Integer id, Role role);


    public List<User> findAll();
}
