package org.example;

import org.apache.ibatis.session.SqlSession;
import org.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.mapper.PersonMapperAnnotation;
import org.util.MyBatisUtils;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public class PersonMapperAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private PersonMapperAnnotation personMapperAnnotation;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        personMapperAnnotation = sqlSession.getMapper(PersonMapperAnnotation.class);
    }

    @Test
    public void getPersonById() {

        Person person = personMapperAnnotation.getPersonById(1);
        System.out.println("person----" + person);
        if(sqlSession != null) {
            sqlSession.close();
        }

    }
}
