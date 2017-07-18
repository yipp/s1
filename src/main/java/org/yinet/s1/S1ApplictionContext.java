package org.yinet.s1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class S1ApplictionContext {
    public static ApplicationContext ctx = null;
    static {
        //ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
