package com.yasso.dfbb.spring.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 16:49
 */
@Service
@RequiredArgsConstructor
public class Tr1 {

    private final Tr2 tr2;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer t1() {
        return tr2.t1();
    }


}
