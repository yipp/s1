package org.yinet.s1.serializer.protostuffer;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/7/23******
 */
public class Test {
    private long id;
    private SerializerTest test;

    public Test() {
    }

    public Test(long id, SerializerTest test) {

        this.id = id;
        this.test = test;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SerializerTest getTest() {
        return test;
    }

    public void setTest(SerializerTest test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", test=" + test +
                '}';
    }
}
