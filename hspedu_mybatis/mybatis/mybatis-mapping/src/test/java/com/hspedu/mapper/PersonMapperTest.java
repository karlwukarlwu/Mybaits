package com.hspedu.mapper;

import com.hspedu.entity.Person;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class PersonMapperTest {

    //属性
    private SqlSession sqlSession;
    private PersonMapper personMapper;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        personMapper = sqlSession.getMapper(PersonMapper.class);
    }

    @Test
    public void getPersonById() {
        Person person = personMapper.getPersonById(1);
        System.out.println("person--" + person);
        if (sqlSession != null) {
            sqlSession.close();
        }

    }

    @Test
    public void getPersonById2() {
        Person person = personMapper.getPersonById2(1);
        System.out.println("person---------" + person);
        if (sqlSession != null) {
            sqlSession.close();
        }

    }
}
