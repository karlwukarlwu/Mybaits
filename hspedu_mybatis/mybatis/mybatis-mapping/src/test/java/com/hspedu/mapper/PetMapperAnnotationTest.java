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
public class PetMapperAnnotationTest {
    //属性
    private SqlSession sqlSession;
    private PetMapperAnnotation petMapperAnnotation;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        petMapperAnnotation = sqlSession.getMapper(PetMapperAnnotation.class);
    }

    @Test
    public void getPetByUserId() {

        List<Pet> pets = petMapperAnnotation.getPetByUserId(1);
        for (Pet pet : pets) {
            System.out.println("宠物信息-" + pet.getId() + "-" + pet.getNickname());
        }

        if(sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void getPetById() {

        Pet pet = petMapperAnnotation.getPetById(1);
        System.out.println("pet信息-" + pet.getId() + "-" +pet.getNickname());
        User user = pet.getUser();
        System.out.println("user信息-" + user.getId() + "-" + user.getName());
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
