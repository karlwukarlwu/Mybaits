package org.example;

import org.apache.ibatis.session.SqlSession;
import org.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.mapper.IdenCardMapper;
import org.mapper.PersonMapper;
import org.util.MyBatisUtils;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public class ForT {
    private SqlSession sqlSession;
    private IdenCardMapper idenCardMapper;
    private PersonMapper personMapper;

    @Before
    public void before() {
        sqlSession = MyBatisUtils.getSqlSession();
//        idenCardMapper = sqlSession.getMapper(IdenCardMapper.class);
        personMapper = sqlSession.getMapper(PersonMapper.class);
    }

    @Test
    public void test() {
        System.out.println(idenCardMapper.getIdenCardById(1));
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
        System.out.println("person--" + person);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}

