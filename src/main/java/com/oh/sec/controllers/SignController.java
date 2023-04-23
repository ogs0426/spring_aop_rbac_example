package com.oh.sec.controllers;

import com.oh.sec.dto.Login;
import com.oh.sec.dto.UserReq;
import com.oh.sec.models.User;
import com.oh.sec.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final UserService userService;

        @PostMapping("/signup")
        public ResponseEntity<User> createUser(@RequestBody UserReq userReq) {

            User user = new User();
            user.setName(userReq.getName());
            user.setPassword(userReq.getPassword());

            User user2 = userService.save(user);

            if (user2 != null)
                return ResponseEntity.ok(user2);

            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }


        @PostMapping("/login")
        public ResponseEntity<User> login(@RequestBody Login login, HttpServletResponse response) {
            User user = userService.login(login);

            // Session 처리
            if(user != null) {
                Cookie idCookie = new Cookie("session_id", user.getId().toString());
                response.addCookie(idCookie);

                return ResponseEntity.ok(user);
            }

            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }
}