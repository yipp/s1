package org.yinet.s1.cache.core;

import io.netty.channel.Channel;
import org.yinet.s1.dao.po.basic.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class UserCache {
    /**channel和player的缓存*/
    public static Map<Channel,Player> playerMap = new HashMap<>();
    /**id和player的缓存*/
    public static Map<Integer,Player> playerId = new HashMap<>();
    /**channel和账号的缓存*/
    public static Map<Channel,String> accountMap = new HashMap<>();
    /**channel和id缓存*/
    public static Map<Channel,Integer> idMap = new HashMap<>();
    /**id和channel的缓存*/
    public static Map<Integer,Channel> mapId = new HashMap<>();
}
