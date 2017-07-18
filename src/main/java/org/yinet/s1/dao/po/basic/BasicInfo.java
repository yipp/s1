package org.yinet.s1.dao.po.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinet.s1.dao.po.impl.AbstractUserImpl;

/**
 * Created by ppdashi on 2017/7/9.
 */
@Service
public class BasicInfo extends AbstractUserImpl {
        private int id;
        private String userName;
        private String password;
        private String qq;
        private String phone;
        private int age;
        private int head;//头像
        private int headHint;//头像框

    public BasicInfo() {
    }

    public BasicInfo(int id, int age, int head, int headHint, String userName, String password, String qq, String phone) {
            this.id = id;
            this.userName = userName;
            this.password = password;
            this.qq = qq;
            this.phone = phone;
            this.age = age;
            this.head = head;
            this.headHint = headHint;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHead() {
            return head;
        }

        public void setHead(int head) {
            this.head = head;
        }

        public int getHeadHint() {
            return headHint;
        }

        public void setHeadHint(int headHint) {
            this.headHint = headHint;
        }

        @Override
        public String toString() {
            return "BisocInfo{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", qq='" + qq + '\'' +
                    ", phone='" + phone + '\'' +
                    ", age=" + age +
                    ", head=" + head +
                    ", headHint=" + headHint +
                    '}';
        }

    }

