package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  Simple Contoller functional test
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {
    // Access path(ip:port/parent-router/sub-router): localhost:8080/alpha/hello
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "<h1>Hello, Spring Boot!</h1>";
    }
}
