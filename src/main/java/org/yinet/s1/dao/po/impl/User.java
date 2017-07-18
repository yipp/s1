package org.yinet.s1.dao.po.impl;

import org.yinet.s1.dao.po.impl.AbstractUserImpl;

/**
 * Created by ppdashi on 2017/7/9.
 */
public interface User {
    /**更新到数据库中*/
    void update(AbstractUserImpl user);
    /**添加新的对象到数据库中*/
    void insert();
    /**删除对象*/
    void delete();
    /**查找对象*/
    void select(int id);
    void select(String username);
    /**查找多个对象*/
    void selectAll();
}
