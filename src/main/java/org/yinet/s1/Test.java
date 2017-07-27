package org.yinet.s1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/21******
 */
@Service
public class Test {
    @Autowired
    private Test1 test1;
    public void  syo(){
        System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        test1.ss();
    }
}
