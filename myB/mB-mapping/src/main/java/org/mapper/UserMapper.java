package org.mapper;

import org.entity.User;


/**
 * @author 韩顺平
 * @version 1.0
 */
public interface UserMapper {

    //通过id获取User对象
    public User getUserById(Integer id);

}
