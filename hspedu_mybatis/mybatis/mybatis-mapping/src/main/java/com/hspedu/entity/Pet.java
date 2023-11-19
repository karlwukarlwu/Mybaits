package com.hspedu.entity;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Pet {
    /**
     * CREATE TABLE mybatis_pet
     * (id INT PRIMARY KEY AUTO_INCREMENT,
     *  nickname VARCHAR(32) NOT NULL DEFAULT '',
     *  user_id INT ,
     * FOREIGN KEY (user_id) REFERENCES mybatis_user(id)
     * )CHARSET=utf8 ;
     */
    private Integer id;
    private String nickname;
    //一个pet对应一个主人 User对象
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //@Override
    //public String toString() {
    //    return "Pet{" +
    //            "id=" + id +
    //            ", nickname='" + nickname + '\'' +
    //            ", user=" + user +
    //            '}';
    //}
}
