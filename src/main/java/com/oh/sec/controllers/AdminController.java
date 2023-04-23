package com.oh.sec.controllers;

import com.oh.sec.annotation.RequiresRight;
import com.oh.sec.dto.Login;
import com.oh.sec.dto.RoleReq;
import com.oh.sec.dto.UserReq;
import com.oh.sec.models.Right;
import com.oh.sec.models.Role;
import com.oh.sec.models.User;
import com.oh.sec.services.RoleService;
import com.oh.sec.services.UserService;
import com.oh.sec.util.Cookieman;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @RequiresRight(value = "1")
    @PutMapping("/admin/user/{userid}/role")
    public ResponseEntity<User> updateRole(@PathVariable String userid, @RequestBody RoleReq roleReq) {

        Role role = roleService.findRoleByName(roleReq.getName());

        User itme = userService.addRole(Integer.valueOf(userid), role);

        if (itme != null)
            return ResponseEntity.ok(itme);

        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

    @RequiresRight(value = "1")
    @DeleteMapping("/admin/user/{userid}/role")
    public ResponseEntity<User> deleteRole(@PathVariable String userid, @RequestBody RoleReq roleReq) {

        Role role = roleService.findRoleByName(roleReq.getName());

        User itme = userService.delRole(Integer.valueOf(userid), role);

        if (itme != null)
            return ResponseEntity.ok(itme);

        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

}