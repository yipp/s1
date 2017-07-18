package org.yinet.s1.serializer.protostuffer;

import org.yinet.s1.net.tcp.model.ResponseSerializer;

import java.util.Arrays;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class Main {
    public static void main(String[] args){
        Object s = new SerializerTest("易","",23,"理工");
        Object buf = ProtostuffUtils.serializer(s);
        System.out.println(Arrays.toString((byte[]) buf));
        SerializerTest test = ProtostuffUtils.deserializer((byte[]) buf,SerializerTest.class);
        System.out.println(test);
    }
}
