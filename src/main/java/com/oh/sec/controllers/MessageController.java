package com.oh.sec.controllers;

import com.oh.sec.annotation.RequiresRight;
import com.oh.sec.dto.Login;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserService userService;

}