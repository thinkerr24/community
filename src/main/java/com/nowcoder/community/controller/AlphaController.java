package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    // Get request (@GetMapping = @RequestMapping(method = RequestMethod.GET))
    // Passing parameters case 1:  students?current=1&limit=20
    @GetMapping(path = "/students")
    @ResponseBody
    public String getStudents(@RequestParam(name="current", required = false, defaultValue = "1") int current,
      @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "Some students";
    }

    // Passing parameters case 2: /student/123
    @GetMapping("/student/{id}")
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // Post request
    @PostMapping("/student")
    @ResponseBody
    // parameter name same as front-end html(resources/static/html/student.html) form name-attribute name
    public String saveStudent(String name, int age) {
        System.out.println("name:" + name + ", age:" + age);
        return "success";
    }

    // Response html data
    @GetMapping("/teacher")
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "ZhangSan");
        mav.addObject("age", 30);
        // Set viewPath:  In /resource/templates/, it is demo/view.html
        mav.setViewName("/demo/view");
        return mav;
    }

    // Similar to the example above: return viewPath & model obj set value.
    @GetMapping("/school")
    public String getSchool(Model model) {
        model.addAttribute("name", "JLD");
        model.addAttribute("age", 5);
        return "/demo/view";
    }

    // Response Json
    // Java Obj(back-end) -> JSON -> Js Obj(front-end)
    @GetMapping("/emp")
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Lisi");
        emp.put("age", 23);
        emp.put("salary", 5000);
        return emp;
    }

    @GetMapping("/emps")
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> emps = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Lisi");
        emp.put("age", 23);
        emp.put("salary", 5000);
        emps.add(emp);

        emp = new HashMap<>();
        emp.put("name", "WangWu");
        emp.put("age", 24);
        emp.put("salary", 6000);
        emps.add(emp);

        emp = new HashMap<>();
        emp.put("name", "UFO");
        emp.put("age", "??");
        emps.add(emp);

        return emps;
    }
}

/*  Note:
 *  1. Scan scope: Main-method-class(Configuration class: CommunityApplication) same level or its sub-package.
 *  2. @Controller @Service @Repository are both based on @Component, choosing one according to the situation.
 *  3.
 */