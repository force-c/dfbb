package com.yasso.dfbb.dao;

import com.yasso.dfbb.entity.TestBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestDao extends CrudRepository<TestBean,Long> {

    List<TestBean> findByName(String name);

    List<TestBean> findByNameOrDesc(String name,String desc);
}
