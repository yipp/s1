package org.yinet.s1.error;

import io.netty.channel.Channel;
import org.yinet.s1.net.tcp.model.Response;
import org.yinet.s1.serializer.protostuffer.ProtostuffUtils;

/**
 * 普通异常，需要返回客户端
 * Created by ppdashi on 2017/7/15.
 */
public class AppGeneralError extends Error{
    public AppGeneralError(Channel channel, String msg) {
        super(channel, msg);
    }

    @Override
    protected void executor() {
        this.logger.info(this.getMessage());
        Response response = new Response();
        ResponesErrorMsg responesErrorMsg = new ResponesErrorMsg(this.getMsg());
        response.setId(404);
        byte[] buf = ProtostuffUtils.serializer(responesErrorMsg);
        response.setDATA(buf);
        this.getChannel().writeAndFlush(response);
    }
}
