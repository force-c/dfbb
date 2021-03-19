package com.yasso.dfbb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JackSon工具类
 * @author guochuang
 * @version 1.0
 * @date 2021/2/26 10:29
 */
public class JackSonUtil {

    private static ObjectMapper objectMapper;

    public static ObjectMapper objectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
