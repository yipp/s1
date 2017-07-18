package org.yinet.s1.error;

import io.netty.channel.Channel;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 需要存储到文件里的异常
 * Created by ppdashi on 2017/7/15.
 */
public class AppRecordError extends Error{
    public AppRecordError(Channel channel, String msg) {
        super(channel, msg);
    }
    @Override
    protected void executor() {
        this.logger.error(this.getMessage());
    }
}
