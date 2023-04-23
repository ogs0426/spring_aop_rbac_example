package com.oh.sec.controllers;

import com.oh.sec.annotation.RequiresRight;
import com.oh.sec.dto.Login;
import com.oh.sec.dto.RoleReq;
import com.oh.sec.dto.UserReq;
import com.oh.sec.models.User;
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
public class UserController {

    private final UserService userService;

    @RequiresRight(value = "1")
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequiresRight(value = "2")
    @GetMapping("/users/{userid}")
    public ResponseEntity<User> getUsers(HttpServletRequest httpServletRequest, @PathVariable String userid) {
        String uid = Cookieman.GetCookieUserId(httpServletRequest);

        if(! userid.equals(uid) ) {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        }

        User user = userService.findByid(Integer.valueOf(uid));

        if (user != null)
            return ResponseEntity.ok(user);

        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

    @RequiresRight(value = "3")
    @PutMapping("/users/{userid}")
    public ResponseEntity<User> updateUsers(HttpServletRequest httpServletRequest, @PathVariable String userid, @RequestBody UserReq userReq) {
        String uid = Cookieman.GetCookieUserId(httpServletRequest);

        if(! userid.equals(uid) ) {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        }

        User user = userService.findByid(Integer.valueOf(uid));

        if (user != null) {
            user.setName(userReq.getName());
            user.setPassword(userReq.getPassword());

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }
}