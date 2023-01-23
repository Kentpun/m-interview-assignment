package com.acmebank.accountmanager.exception;

import com.acmebank.accountmanager.utils.response.Res;
import com.acmebank.accountmanager.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public <T> Result<T> error(BizException e){
        return (Result<T>) Res.error(e.getResponse(), e.getMap());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<String> details = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity(Res.fail(details), HttpStatus.BAD_REQUEST);
    }
}
