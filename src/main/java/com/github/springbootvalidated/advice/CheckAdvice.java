package com.github.springbootvalidated.advice;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * <p>
 * 创建时间为 15:19 2019-04-17
 * 项目名称 spring-boot-validated
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */


@RestControllerAdvice
public class CheckAdvice {

    /**
     * 请求的 JSON 参数在请求体内的参数校验
     *
     * @param e 异常信息
     * @return 返回数据
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBindException1(MethodArgumentNotValidException e) {
        e.getBindingResult().getAllErrors().forEach(System.out::println);
        return new ResponseEntity<>("ERROR:" + JSON.toJSONString(e.getBindingResult().getAllErrors()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求的 URL 参数检验
     *
     * @param e 异常信息
     * @return 返回提示信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleBindException2(ConstraintViolationException e) {
        e.getConstraintViolations().forEach(System.out::println);
        return "ConstraintViolationException";
    }

}
