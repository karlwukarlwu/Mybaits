package com.hspedu.entity;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class User {

    /**
     * CREATE TABLE mybatis_user
     * (id INT PRIMARY KEY AUTO_INCREMENT,
     * NAME VARCHAR(32) NOT NULL DEFAULT ''
     * )CHARSET=utf8 ;
     */

    private Integer id;
    private String name;
    //因为一个user可以养多个宠物,mybatis 使用集合List<Pet>体现这个关系
    private List<Pet> pets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    //这toString会带来麻烦?=>会造成StackOverFlow
    //@Override
    //public String toString() {
    //    return "User{" +
    //            "id=" + id +
    //            ", name='" + name + '\'' +
    //            ", pets=" + pets +
    //            '}';
    //}
}
