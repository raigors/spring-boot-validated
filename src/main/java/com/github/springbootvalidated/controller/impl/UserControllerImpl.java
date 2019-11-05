package com.github.springbootvalidated.controller.impl;

import com.github.springbootvalidated.annotation.Cross;
import com.github.springbootvalidated.controller.IUserController;
import com.github.springbootvalidated.pojo.UserInfoDO;
import com.github.springbootvalidated.service.IUserService;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 创建时间为 下午6:11 2019/9/27
 * 项目名称 spring-boot-validated
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Validated
@RestController
public class UserControllerImpl implements IUserController {

    @Resource
    private IUserService service;

    @PostMapping("/user")
    @Override
    public UserInfoDO createUser(@RequestBody @Valid UserInfoDO userInfoDO) {
        return userInfoDO;
    }

    @GetMapping("/user/age/{age}")
    @Override
    public List<UserInfoDO> getUsersByAge(@PathVariable @Range(min = 0, max = 1000) Integer age) {
        return service.getUsersByAge(age);
    }

    @Cross
    @GetMapping("/user/{pass1}/{pass2}")
    @Override
    public String passCheck(@PathVariable String pass1, @PathVariable String pass2) {
        return pass1 + ":" + pass2;
    }

}