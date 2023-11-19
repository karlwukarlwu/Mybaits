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
