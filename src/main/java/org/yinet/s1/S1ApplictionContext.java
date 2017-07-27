package org.yinet.s1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yinet.s1.logic.scenes.Card.CardInit;
import org.yinet.s1.logic.scenes.manager.Timer;
import org.yinet.s1.net.tcp.TcpUtils;
import org.yinet.s1.serializer.excel.ExcelUtils;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class S1ApplictionContext {
    public static ApplicationContext ctx = null;
    static {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    public static void main(String[] args) throws Exception {
       init();
    }
    private static void init(){
        CardInit init = ctx.getBean(CardInit.class);
        init.addCar();
        Timer timer = ctx.getBean(Timer.class);
        timer.run();
        ExcelUtils.init();
        TcpUtils.start(7788);
    }
}
