package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 *  Simple Contoller functional test
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService service;

    // Access path(ip:port/parent-router/sub-router): localhost:8080/alpha/hello
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "<h1>Hello, Spring Boot!</h1>";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return service.find();
    }

    // Example of request&response in Controller, access url:http://localhost:8080/community/alpha/http?code=77r
    @RequestMapping("/http")
    public void http(HttpServletRequest req, HttpServletResponse res) {
        // Get request data
        System.out.println(req.getMethod());
        System.out.println(req.getServletPath());
        Enumeration<String> enumeration = req.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(req.getParameter("code"));

        // return response data
        res.setContentType("text/html;charset=utf-8");
        try( PrintWriter writer = res.getWriter();
        ) {

            writer.write("<h1>Welcome learn Spring Boot!</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*  Note:
 *  1. Scan scope: Main-method-class(Configuration class: CommunityApplication) same level or its sub-package.
 *  2. @Controller @Service @Repository are both based on @Component, choosing one according to the situation.
 *  3.
 */