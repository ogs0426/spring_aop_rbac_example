package com.oh.sec.config;

import com.oh.sec.annotation.RequiresRight;
import com.oh.sec.annotation.RequiresRole;
import com.oh.sec.models.Right;
import com.oh.sec.models.Role;
import com.oh.sec.models.User;
import com.oh.sec.services.UserService;
import com.oh.sec.util.Cookieman;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.AccessDeniedException;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    private UserService userService;

    @Around("@annotation(requiresRight)")
    public Object requiresRight(ProceedingJoinPoint joinPoint, RequiresRight requiresRight) throws Throwable {

        String right = requiresRight.value();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String uid = Cookieman.GetCookieUserId(request);

        if (uid != null ) {
            User user = userService.findByid(Integer.parseInt(uid));

            if (user != null) {
                for (Role r:user.getRoles()) {
                    for (Right s:r.getRights()) {
                        if(right.equals(s.getId().toString()))
                            return joinPoint.proceed();
                    }
                }
            }
        }

        throw new AccessDeniedException("Access is denied");
    }

    @Around("@annotation(requiresRole)")
    public Object requiresRole(ProceedingJoinPoint joinPoint, RequiresRole requiresRole) throws Throwable {

        String roles = requiresRole.value();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String uid = Cookieman.GetCookieUserId(request);

        if (uid != null ) {
            User user = userService.findByid(Integer.parseInt(uid));

            if (user != null) {
                for (Role r:user.getRoles()) {
                    if(roles.equals(r.getId().toString()))
                        return joinPoint.proceed();
                }
            }
        }

        throw new AccessDeniedException("Access is denied");
    }

}