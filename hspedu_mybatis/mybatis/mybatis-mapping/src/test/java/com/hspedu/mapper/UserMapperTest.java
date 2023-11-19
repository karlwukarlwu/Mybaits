package com.hspedu.mapper;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
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

        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("养的宠物信息-" + pet.getId() + "-" + pet.getNickname());
        }
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
