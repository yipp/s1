package org.yinet.s1.net.tcp.process;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;
import org.yinet.s1.S1ApplictionContext;
import org.yinet.s1.executor.ExecutorUtils;
import org.yinet.s1.logic.LogicTask;
import org.yinet.s1.net.tcp.model.Request;

/**
 * Created by ppdashi on 2017/7/15.
 * 消息分发器
 */
@Component
public class MassegeHandOut {
    public void process(Channel channel, Request request){
            //将客户端发过来的协议id拼接到"Action_"字符串中
            String className = "action_"+request.getId();
            LogicTask logic = null;
            try{
                //根据拼接的字符串用spring ioc容器的依赖注入实例化协议实现具体对象
                logic = (LogicTask) S1ApplictionContext.ctx.getBean(className);
                logic.setId(request.getId());
                logic.setChannel(channel);
                logic.setMsg(request.getDATA());
                //提交线程池释放io线程
                ExecutorUtils.threadTask.execute(logic);

            }catch(Exception e){
                throw new RuntimeException("找不到Action_"+request.getId()+"这个脚本类");
            }
        }
}
