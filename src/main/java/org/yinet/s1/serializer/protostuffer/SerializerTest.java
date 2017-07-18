package org.yinet.s1.serializer.protostuffer;

import org.yinet.s1.net.tcp.model.ResponseSerializer;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class SerializerTest implements ResponseSerializer{
    private String name;
    private String studentNo;
    private int age;
    private String schoolName;

    public SerializerTest() {
    }

    public SerializerTest(String name, String studentNo, int age, String schoolName) {
        this.name = name;
        this.studentNo = studentNo;
        this.age = age;
        this.schoolName = schoolName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    @Override
    public String toString() {
        return "SerializerTest{" +
                "name='" + name + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", age=" + age +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
