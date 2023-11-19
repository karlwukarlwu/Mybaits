package com.hspedu.mapper;

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

        sqlSession = MyBatisUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("jack");
        user.setUseremail("jack@qq.com");

        userMapper.addUser(user);

        //如果是增删改, 需要commit()
        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作OK~");
    }

    @Test
    public void findAllUser() {
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println("user--" + user);
        }

        if(sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作OK~");
    }

}
