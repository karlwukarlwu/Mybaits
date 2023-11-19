package mapper;

import org.apache.ibatis.session.SqlSession;
import org.entity.Monk;
import org.entity.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mapper.MonkMapper;
import org.util.MyBatisUtils;

import java.util.Date;
import java.util.List;

/**
 * Karl Rules!
 * 2023/11/16
 * now File Encoding is UTF-8
 */
public class MonkMapperTest {
//    需要在外界声名 其他的@Test才能用
    private SqlSession sqlSession;
    private MonkMapper monkMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monkMapper = sqlSession.getMapper(MonkMapper.class);
        System.out.println("mapper = " + monkMapper.getClass());
    }

    @Test
    public void addMonk() {

        for (int i = 0; i < 2; i++) {
            Monk monk = new Monk();
            monk.setAge(10 + i);
            monk.setBirthday(new Date());
            monk.setName("heshang" + i);
            monkMapper.addMonk(monk);
            System.out.println("添加对象--" + monk);
            System.out.println("添加到表中后, 自增长的id=" + monk.getId());


        }
        //如果是增删改, 需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功...");
    }

    @Test
    public void deleteMonk() {
        monkMapper.delMonk(1);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void updateMonk() {
        Monk monk = new Monk();
        monk.setId(2);
        monk.setAge(100);
        monk.setBirthday(new Date());
        monk.setName("heshang2");
        monkMapper.updateMonk(monk);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void selectMonkByName() {
        Monk monk = monkMapper.getMonkById(2);
        System.out.println("monk = " + monk);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void selectMonk() {
        List<Monk> allMonk = monkMapper.findAllMonk();
        System.out.println("monk = " + allMonk);
        sqlSession.commit();
        sqlSession.close();
    }

}
