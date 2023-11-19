package com.hspedu.mapper;

import com.hspedu.entity.IdenCard;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class IdenCardMapperAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private IdenCardMapperAnnotation idenCardMapperAnnotation;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        idenCardMapperAnnotation = sqlSession.getMapper(IdenCardMapperAnnotation.class);
    }

    @Test
    public void getIdenCardById() {

        IdenCard idenCard =
                idenCardMapperAnnotation.getIdenCardById(1);

        System.out.println("idenCard--注解--" + idenCard);

        if(sqlSession != null) {
            sqlSession.close();
        }

    }
}
