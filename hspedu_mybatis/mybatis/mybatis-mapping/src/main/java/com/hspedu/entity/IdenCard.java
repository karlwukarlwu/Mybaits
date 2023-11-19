package com.hspedu.entity;

import com.hspedu.mapper.PersonMapper;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class IdenCard {
    /**
     * CREATE TABLE idencard
     * (
     * id INT PRIMARY KEY AUTO_INCREMENT,
     * card_sn VARCHAR(32) NOT NULL DEFAULT ''
     * )CHARSET utf8 ;
     */
    private Integer id;
    private String card_sn;

    //通过查询IdenCard 可以级联查询得到person
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_sn() {
        return card_sn;
    }

    public void setCard_sn(String card_sn) {
        this.card_sn = card_sn;
    }

    @Override
    public String toString() {
        return "IdenCard{" +
                "id=" + id +
                ", card_sn='" + card_sn + '\'' +
                ", person=" + person +
                '}';
    }
}
