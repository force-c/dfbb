package com.yasso.dfbb.service;

import com.yasso.dfbb.entity.TestBean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {
    Iterable<TestBean> findAll();

    void save(List<TestBean> list);

    void save(TestBean bean);

    List<TestBean> findByName(String text);

    List<TestBean> findByNameOrDesc(String name,String desc);
}
