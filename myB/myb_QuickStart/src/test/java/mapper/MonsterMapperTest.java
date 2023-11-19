package mapper;

import org.apache.ibatis.session.SqlSession;
import org.entity.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mapper.MonsterMapper;
import org.util.MyBatisUtils;

import java.util.Date;
import java.util.List;

/**
 * Karl Rules!
 * 2023/11/16
 * now File Encoding is UTF-8
 */
public class MonsterMapperTest {
    private SqlSession sqlSession;
    //  我们自己写的接口
    private MonsterMapper monsterMapper;

    //  当我们用了before注解 意味着这个方法会在每个测试方法前执行
    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
//       拿到MonsterMapper的代理对象
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
//        monsterMapper=class com.sun.proxy.$Proxy7
        System.out.println("monsterMapper=" + monsterMapper.getClass());
    }

    @Test
    public void testAddMonster() {
//        这个方法执行之前会走@Before注解的方法
//        monsterMapper.addMonster(null);
//        sqlSession.commit();
    }

    @Test
    public void t1() {
        System.out.println("t1");
    }

    @Test
    public void addMonster() {

        for (int i = 0; i < 2; i++) {
            Monster monster = new Monster();
            monster.setAge(10 + i);
            monster.setBirthday(new Date());
            monster.setEmail("kate@qq.com");
            monster.setGender(1);
            monster.setName("elephant-" + i);
            monster.setSalary(1000 + i * 10);
            monsterMapper.addMonster(monster);

            System.out.println("添加对象--" + monster);
            System.out.println("添加到表中后, 自增长的id=" + monster.getId());

        }
        //如果是增删改, 需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功...");
    }

    @Test
    public void delMonster() {
        monsterMapper.delMonster(1);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("删除成功...");
    }
    @Test
    public void updateMonster() {
        Monster monster = new Monster();
        monster.setAge(20);
        monster.setBirthday(new Date());
        monster.setEmail("kate3@qq.com");
        monster.setGender(1);
        monster.setName("nmw-100");
        monster.setSalary(1000);
        monster.setId(5);//这个一定要有，如果没有就不知道修改哪个对象
//        这里传monster是因为在MonsterMapper.xml中的update标签中有parameterType="org.entity.Monster"
//        但是具体的mysql语句还是拿id属性去找
        monsterMapper.updateMonster(monster);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("修改成功...");
    }
    @Test
    public void getMonsterById() {

        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);

        if(sqlSession != null) {
//            查询不用提交 但是需要关闭流
            sqlSession.close();
        }
        System.out.println("查询成功~");
    }


    @Test
    public void findAllMonster() {

        List<Monster> monsters = monsterMapper.findAllMonster();
        for (Monster monster : monsters) {
            System.out.println("monster-" + monster);
        }
        if(sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功~");
    }
}
