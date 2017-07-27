package org.yinet.s1.dao.po.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yinet.s1.dao.manager.SerializerPlayerModel;

public class test {
    public static void main(String[] args) {  
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        SerializerPlayerModel serializerPlayerMaodel = ctx.getBean(SerializerPlayerModel.class);
        Player player = serializerPlayerMaodel.getPlayer("12345678");
        System.err.println(player);
    }  
}  