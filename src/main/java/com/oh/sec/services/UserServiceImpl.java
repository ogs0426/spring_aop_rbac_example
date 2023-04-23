package com.oh.sec.services;

import com.oh.sec.dto.Login;
import com.oh.sec.models.Role;
import com.oh.sec.models.User;
import com.oh.sec.repositories.RightRepository;
import com.oh.sec.repositories.RoleRepository;
import com.oh.sec.repositories.UserRepository;
import com.oh.sec.security.SnPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RightRepository rightRepository;

    @Transactional
    public void register(User user, String roleName) {
        String encodedPassword = SnPasswordEncoder.encode(user.getPassword());
        // String encodedPassword = user.getPassword();

        user.setPassword(encodedPassword);
        userRepository.register(user, roleName);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByid(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User addRole(Integer id, Role role) {
        User user = userRepository.findById(id).orElse(null);

        if( user != null ) {
            Set<Role> set = user.getRoles();
            set.add(role);

            user.setRoles(set);

            return userRepository.save(user);
        }

        return null;
    }

    @Transactional
    public User delRole(Integer id, Role role) {
        User user = userRepository.findById(id).orElse(null);

        if( user != null ) {
            Set<Role> set = user.getRoles();
            set.remove(role);

            user.setRoles(set);

            return userRepository.save(user);
        }

        return null;
    }

    @Transactional(readOnly = true)
    public User login(Login login) {
        User user = userRepository.findByName(login.getName()).orElse(new User());

        String aa = SnPasswordEncoder.encode(login.getPassword());

        if( user.getPassword() != null && SnPasswordEncoder.matches(login.getPassword(), user.getPassword() ) ) {
            return user;
        }

        return null;
    }
}
