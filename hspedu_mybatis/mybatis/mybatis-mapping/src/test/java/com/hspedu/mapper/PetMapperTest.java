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
public class PetMapperTest {
    //属性
    private SqlSession sqlSession;
    private PetMapper petMapper;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        petMapper = sqlSession.getMapper(PetMapper.class);
    }

    @Test
    public void getPetByUserId() {
        List<Pet> pets = petMapper.getPetByUserId(2);
        for (Pet pet : pets) {
            System.out.println("pet信息-" + pet.getId() + "-" + pet.getNickname());
            User user = pet.getUser();
            System.out.println("user信息 name-" + user.getName());
        }
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
    @Test
    public void getPetById() {
        Pet pet = petMapper.getPetById(2);
        System.out.println("pet信息-" + pet.getId() + "-" + pet.getNickname());
        User user = pet.getUser();
        System.out.println("user信息-" + user.getId() + "-" + user.getName());
        if(sqlSession != null) {
            sqlSession.close();
        }
    }

}

