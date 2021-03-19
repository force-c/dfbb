package com.yasso.dfbb.spring.transaction;

import com.yasso.dfbb.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 17:10
 */
@Service
@RequiredArgsConstructor
public class Tr3 {

    private final LogMapper logMapper;

    @Transactional(propagation = Propagation.MANDATORY)
    public Integer t1(){
        return null;
    }
}
