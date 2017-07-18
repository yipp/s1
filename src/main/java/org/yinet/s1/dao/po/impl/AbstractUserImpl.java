package org.yinet.s1.dao.po.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.yinet.s1.dao.mapper.PlayerMapper;

/**
 * Created by ppdashi on 2017/7/9.
 */
public abstract class AbstractUserImpl implements User {
    public void update(AbstractUserImpl user) {
        String userSerializer = JSON.toJSONString(user);
    }

    public void insert() {

    }

    public void delete() {

    }
    public void selectAll() {

    }

    public void select(int id) {
        //从数据库中获取到对象字符串
        String str = null;
        User user = JSON.parseObject(str,User.class);
    }

    public void select(String username) {

    }
}
