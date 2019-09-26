package com.theo.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author huangsuixin
 * @date 2019/09/26 11:10
 * @description {@link java.util.Properties} {@link RestController}
 */
@RestController
public class PropertiesRestController {

    @PostMapping(value = "add/prop", consumes = "text/properties;charset=UTF-8")
    public Properties addProperties(@RequestBody Properties properties) {
        return properties;
    }
}
