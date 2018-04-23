package io.netty.messagepack.dto;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * 测试编码解码的传输类
 */
@Message
public class UserInfo implements Serializable{

    private String name;
    private int age;

    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
