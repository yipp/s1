package org.yinet.s1.error;

import io.netty.channel.Channel;
import org.apache.log4j.Logger;
import org.yinet.s1.executor.ExecutorUtils;

/**
 * Created by ppdashi on 2017/7/15.
 */
public abstract class Error extends RuntimeException implements Runnable{
    protected Logger logger = Logger.getLogger(Error.class);
    private Channel channel;
    private String msg;

    public Error() {
    }

    public Error(Channel channel, String msg) {
        this.channel = channel;
        this.msg = msg;
        ExecutorUtils.threadTask.execute(this);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    protected abstract void executor();
    @Override
    public void run() {
        executor();
    }
}
