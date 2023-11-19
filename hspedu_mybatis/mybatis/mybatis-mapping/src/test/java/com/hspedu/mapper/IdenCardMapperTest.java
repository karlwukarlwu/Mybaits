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
public class IdenCardMapperTest {

    //属性
    private SqlSession sqlSession;
    private IdenCardMapper idenCardMapper;

    //初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        idenCardMapper = sqlSession.getMapper(IdenCardMapper.class);
    }

    @Test
    public void getIdenCardById() {
        IdenCard idenCard = idenCardMapper.getIdenCardById(1);
        System.out.println("idenCard--" + idenCard);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void getIdenCardById2() {
        IdenCard idenCard = idenCardMapper.getIdenCardById2(200);

        System.out.println("idenCard2--" + idenCard);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
