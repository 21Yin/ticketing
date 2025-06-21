package com.assessment.ticketing.global.utils;

import com.assessment.ticketing.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

@Component
@AllArgsConstructor
@Slf4j
public class HttpRequestUtils {

    private final JwtUtil jwtUtils;

    public Locale getLocale() {
        HttpServletRequest request = getCurrentHttpRequest();
        if (request != null) {
            return request.getLocale();
        }
        return Locale.ENGLISH;
    }

    public String getCurrentLoginId() {
        HttpServletRequest request = getCurrentHttpRequest();
        if (request != null) {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                bearerToken = bearerToken.substring(7);
            }
            return jwtUtils.getUserIdFromJwtToken(bearerToken);
        }
        return null;
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
