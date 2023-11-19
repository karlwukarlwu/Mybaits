package org.mapper;


import org.entity.User;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface UserMapper {

    //添加方法
    public void addUser(User user);

    //查询所有的User
    public List<User> findAllUser();
}
