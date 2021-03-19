package com.yasso.dfbb.spring.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 17:09
 */
@Service
@RequiredArgsConstructor
public class Tr2 {

    private final Tr3 tr3;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer t1(){
      return tr3.t1();
    }
}
