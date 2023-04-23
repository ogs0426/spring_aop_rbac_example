package com.oh.sec.services;

import com.oh.sec.dto.Login;
import com.oh.sec.models.Right;
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

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RightRepository rightRepository;

    @Transactional(readOnly = true)
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
    @Transactional(readOnly = true)
    public Right findRightByName(String name) {
        return rightRepository.findByName(name).orElse(null);
    }

    @Override
    public Role addRight(Integer id, Right role) {
        return null;
    }
}
