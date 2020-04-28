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

/*  Note:
 *  1. Scan scope: Main-method-class(Configuration class: CommunityApplication) same level or its sub-package.
 *  2. @Controller @Service @Repository are both based on @Component, choosing one according to the situation.
 *  3.
 */