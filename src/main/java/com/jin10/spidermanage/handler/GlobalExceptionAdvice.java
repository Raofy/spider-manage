package com.jin10.spidermanage.handler;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.exception.config.ExceptionCodeConfiguration;
import com.jin10.spidermanage.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration exceptionCodeConfiguration;

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public BaseResponse handleException(HttpServletRequest req, Exception e) {
//        String requestURL = req.getRequestURL().toString();
//        String method = req.getMethod();
//        return new BaseResponse(500, "服务器异常", method + " " + requestURL);
//    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<BaseResponse> handleHttpException(HttpServletRequest req, HttpException httpException) {
        String requestURL = req.getRequestURL().toString();
        String method = req.getMethod();
        BaseResponse br = new BaseResponse(httpException.getStatus(), exceptionCodeConfiguration.getMessage(httpException.getStatus()), method + " " + requestURL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(httpException.getHttpStateCode());
        ResponseEntity<BaseResponse> re = new ResponseEntity<>(br, headers, httpStatus);
        return re;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errorMessages = this.formatAllErrorMessages(allErrors);
        return new BaseResponse(400, errorMessages, method + " " + requestURI);
    }

    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorsMessage = new StringBuffer();
        errors.forEach(error ->
                errorsMessage.append(error.getDefaultMessage()).append(";"));
        return errorsMessage.toString();
    }
}
