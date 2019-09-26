package com.theo.web.controller;

import com.theo.web.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsuixin
 * @date 2019/09/25 21:17
 * @description //TODO
 */
@RestController
public class UserRestController {

    @PostMapping("echo/user")
    public User user(@RequestBody User user) {
        return user;
    }
}
