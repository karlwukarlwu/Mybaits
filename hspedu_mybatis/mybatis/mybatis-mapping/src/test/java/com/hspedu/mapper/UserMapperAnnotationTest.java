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
public class UserMapperAnnotationTest {
    //属性
    private SqlSession sqlSession;
    private UserMapperAnnotation userMapperAnnotation;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        userMapperAnnotation = sqlSession.getMapper(UserMapperAnnotation.class);
    }

    @Test
    public void getUserById() {

        User user = userMapperAnnotation.getUserById(2);
        System.out.println("user信息-" + user.getId() + "-" + user.getName());

        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("宠物信息-" + pet.getId() + "-" + pet.getNickname());
        }

        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
