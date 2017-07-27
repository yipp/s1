package org.yinet.s1.serializer.protostuffer;

import org.yinet.s1.net.tcp.model.ResponseSerializer;

import java.util.Arrays;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class Main {
    public static void main(String[] args){
        Test test = new Test(100000,null);
        //Object s = new SerializerTest("易","",23,"理工");
        byte[] buf = ProtostuffUtils.serializer(test);
        System.out.println(Arrays.toString((byte[]) buf));
        Test test1 = ProtostuffUtils.deserializer((byte[]) buf,Test.class);
        System.out.println(test1);
    }
}
