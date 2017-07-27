package org.yinet.s1.serializer.json;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.yinet.s1.dao.po.basic.Resource;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");
        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.getUsers().add(guestUser);
        group.getUsers().add(rootUser);
        /**对象转json*/
        String jsonString = JSON.toJSONString(group);

        /**json转对象*/
        Group group2 = JSON.parseObject(jsonString, Group.class);
        List<Integer> list = new ArrayList<>();
        Resource resources = new Resource(1,2,1L,2L,3);
        list.add(1);
        list.add(2);
        list.add(3);
        String res = JSON.toJSONString(list);
        List<Integer> list1 = JSON.parseArray(res,Integer.class);
        System.out.println(list1);
    }
}