package com.yasso.dfbb.script.python;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/11 14:14
 */
public class InvokingPY {

    @SneakyThrows
    public static void py_01() {
        Process process;
        String cmd = "python C:\\Users\\guochuang\\Desktop\\python\\hehe.py";
        process = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        py_01();
    }
}

