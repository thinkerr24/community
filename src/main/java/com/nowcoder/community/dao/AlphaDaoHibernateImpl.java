package com.nowcoder.community.dao;
import org.springframework.stereotype.Repository;

@Repository("alphaHibernate") // Bean name
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
