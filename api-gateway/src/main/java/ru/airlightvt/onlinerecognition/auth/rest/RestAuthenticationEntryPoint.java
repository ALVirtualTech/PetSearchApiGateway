package ru.airlightvt.onlinerecognition.auth.rest;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Обработчик ошибок авторизации, возникающих в процессе цепочки фильтров.
 * Используется ExceptionTranslationFilter при обработке полученной ошибки.
 * TODO: специфицировать именно для ошибок авторизации по REST (кидать специально для этого сделанное исключение)
 *
 * @author apolyakov
 * @since 06.01.2019
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
