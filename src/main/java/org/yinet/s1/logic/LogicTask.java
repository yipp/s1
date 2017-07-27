package org.yinet.s1.logic;
import io.netty.channel.Channel;
import org.apache.poi.ss.formula.functions.T;
import org.yinet.s1.error.AppRecordError;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.net.tcp.model.ResponseSerializer;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

import java.util.Arrays;

/**
 * 业务处理线程事件
 */
public abstract class LogicTask implements Runnable{
    protected Channel channel;
    protected int id;
    protected Object msg;
    public abstract void executor();
    public LogicTask() {
    }

    public LogicTask(Channel channel, int id, Object msg) {
        this.channel = channel;
        this.id = id;
        this.msg = msg;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void run() {
        executor();
    }
    protected void  response(){
//        if(msg instanceof ResponseSerializer)
//            throw new AppRecordError(this.channel,"回发数据序列化类型错误");
        Response response = new Response();
        response.setId(id);
        if(this.msg == null)
            response.setDATA(null);
        else{
            byte[] buf = ProtostuffUtils.serializer(msg);
            response.setDATA(buf);
        }
        channel.writeAndFlush(response);
    }
}
