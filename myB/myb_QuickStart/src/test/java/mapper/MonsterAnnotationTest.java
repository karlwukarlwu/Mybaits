package mapper;

import org.apache.ibatis.session.SqlSession;
import org.entity.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mapper.MonsterAnnotation;
import org.util.MyBatisUtils;

import java.util.Date;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class MonsterAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private MonsterAnnotation monsterAnnotation;
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        monsterAnnotation = sqlSession.getMapper(MonsterAnnotation.class);
        //返回的依然是一个接口的代理对象
        System.out.println("monsterAnnotation--" + monsterAnnotation.getClass());
    }

    @Test
    public void addMonster() {

        Monster monster = new Monster();
        monster.setAge(30);
        monster.setBirthday(new Date());
        monster.setEmail("kate6@qq.com");
        monster.setGender(1);
        monster.setName("狐狸精-100");
        monster.setSalary(1000);
        //使用在接口方法配置注解方式完成对DB操作
        monsterAnnotation.addMonster(monster);
        System.out.println("添加后monster-id-" + monster.getId());

        //如果是增删改, 需要提交事务
        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功...");

    }

}
