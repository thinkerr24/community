package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao dao;

    public AlphaService() {
        System.out.println("AlphaService constructor calls!");
    }

    @PostConstruct
    public void init() {
        System.out.println("AlphaService initialization calls");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AlphaService destroy calls");
    }

    public String find() {
        return dao.select();
    }
}
