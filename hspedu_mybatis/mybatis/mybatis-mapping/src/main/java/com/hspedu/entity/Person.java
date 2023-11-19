package com.hspedu.entity;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Person {

    /**
     * CREATE TABLE person
     * (
     * id INT PRIMARY KEY AUTO_INCREMENT,
     * NAME VARCHAR(32) NOT NULL DEFAULT '',
     * card_id INT ,
     * FOREIGN KEY (card_id) REFERENCES idencard(id)
     * )CHARSET utf8;
     */
    private Integer id;
    private String name;
    //因为我们的需要实现一个级联操作, 一个人需要对应一个身份证
    //这里需要直接定义IdenCard对象属性
    private IdenCard card;

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

    public IdenCard getCard() {
        return card;
    }

    public void setCard(IdenCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card=" + card +
                '}';
    }
}
