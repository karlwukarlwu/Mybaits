package org.example;

import org.apache.ibatis.session.SqlSession;
import org.entity.Pet;
import org.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mapper.UserMapper;
import org.util.MyBatisUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public class UserMapperTest {

    //属性
    private SqlSession sqlSession;
    private UserMapper userMapper;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(2);

        System.out.println("user信息-" + user.getId() + "-" + user.getName());

//        我完全不知道这个底层如何实现的 但是这个双向映射真的强
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("养的宠物信息-" + pet.getId() + "-" + pet.getNickname());
            System.out.println(pet.getUser().getName());
        }
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
