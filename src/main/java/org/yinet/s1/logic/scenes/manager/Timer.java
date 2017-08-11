package org.yinet.s1.logic.scenes.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.S1ApplictionContext;
import org.yinet.s1.executor.ExecutorUtils;
import org.yinet.s1.logic.scenes.manager.Scenes_01.manager.Scenes_01;
import org.yinet.s1.logic.scenes.manager.Scenes_03.manager.Scenes_03Manager;
import org.yinet.s1.logic.scenes.manager.scenes02.manager.Scenes_02;
import org.yinet.s1.net.tcp.model.Response;

import java.util.concurrent.TimeUnit;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/19******
 * 场景定时器每秒钟向客户端发送一次消息
 */
@Service
public class Timer {
    @Autowired
    private Scenes_01 scenes_01;
    @Autowired
    private Scenes_02 scenes_02;
    @Autowired
    private Scenes_03Manager scenes_03;
    private int timer_01 = 7;
    private boolean end_01 = true;
    public void run(){
        scenes_01 = (Scenes_01) S1ApplictionContext.ctx.getBean("scenes_01");
        ExecutorUtils.timerTask.scheduleAtFixedRate(new Runnable() {
            public void run() {
                scense_01Timer();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
    private void scense_01Timer(){
        timer_01--;
        sendTimer();//发送时间到客户端
        System.err.println(timer_01);
        if(timer_01 == 3 && end_01){
            end_01 = false;
            ExecutorUtils.threadTask.execute(new Runnable() {
                @Override
                public void run() {
                    scenes_01.doExecutor();
                    scenes_02.doExecutor();
                    scenes_03.doExecutor();
                }
            });
        }
        if(timer_01 == 0){
            ExecutorUtils.threadTask.execute(new Runnable() {
                @Override
                public void run() {
                    scenes_01.clear();
                    scenes_02.clear();
                    scenes_03.clear();
                }
            });
            end_01 = true;
            timer_01 = 9;
        }
    }
    @Autowired
    private Response response;
    private void sendTimer(){
        for (Channel channel:Scenes_01.user)
            massege(channel);
        for (Channel channel:Scenes_02.user)
            massege(channel);
        for (Channel channel:scenes_03.getUser())
            massege(channel);
    }
    private void massege(Channel channel){
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(timer_01);
        response.setId(200);
        response.setDATA(buf.array());
        channel.writeAndFlush(response);
    }
}
