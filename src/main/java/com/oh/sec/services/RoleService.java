package com.oh.sec.services;

import com.oh.sec.dto.Login;
import com.oh.sec.models.Right;
import com.oh.sec.models.Role;
import com.oh.sec.models.User;

import java.util.List;

public interface RoleService {

    public Role findRoleByName(String name);
    public Right findRightByName(String name);

    public Role addRight(Integer id, Right role);

}
