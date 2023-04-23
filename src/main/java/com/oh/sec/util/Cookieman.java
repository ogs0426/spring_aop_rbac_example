package com.oh.sec.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public final class Cookieman {

        public static String GetCookieUserId(HttpServletRequest request) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("session_id")) {
                        return cookie.getValue();
                    }
                }
            }

            return null;
        }
}
