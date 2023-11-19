package mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.util.MyBatisUtils;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class MyBatisNativeTest {
    //属性
    private SqlSession sqlSession;

    //编写方法完成初始化
    @Test
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //sqlSession 返回的对象是 DefaultSqlSession
        System.out.println("sqlSession--" + sqlSession.getClass());

    }
}
