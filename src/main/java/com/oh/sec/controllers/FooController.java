package com.oh.sec.controllers;

import com.oh.sec.annotation.RequiresRight;
import com.oh.sec.dto.Login;
import com.oh.sec.models.User;
import com.oh.sec.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FooController {

    private final UserService userService;

    @RequiresRight(value = "4")
    @GetMapping("/foo")
    public String getFoo() {
        return "foo";
    }

    @RequiresRight(value = "4")
    @GetMapping("/var")
    public String getVar() {
        return "var";
    }
}