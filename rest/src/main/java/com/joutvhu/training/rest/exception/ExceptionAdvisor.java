package com.joutvhu.training.rest.exception;

import com.joutvhu.training.rest.model.view.RestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionAdvisor {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class, LoginException.class})
    public RestResponse accessDeniedExceptionHandler(Exception e) {
        log.warn(e.getMessage(), e);
        return new RestResponse(HttpStatus.FORBIDDEN, "Access Denied");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, NullPointerException.class})
    public RestResponse exceptionNullHandler(Exception e) {
        log.warn(e.getMessage(), e);
        return new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
