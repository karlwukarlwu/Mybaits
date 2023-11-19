package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class MonsterMapperTest {
    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    /**
     * 老师解读
     * 1. 当方法标注 @Before, 表示在执行你的目标测试方法前，会先执行该方法
     * 2. 这里在测试的时候，可能小伙伴们会遇到一些麻烦，老师说了解决方案
     */
    //编写方法完成初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //获取到到MonsterMapper对象 class com.sun.proxy.$Proxy7 代理对象
        //, 底层是使用了动态代理机制, 后面我们自己实现mybatis底层机制时，会讲到
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper=" + monsterMapper.getClass());

    }


    @Test
    public void getMonsterById() {

        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功~~~~");
    }


    //测试一级缓存
    @Test
    public void level1CacheTest() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);
        monsterMapper.getMonsterById(8);


        //再次查询id=3的monster
        //当我们再次查询 id=3的Monster时，直接从一级缓存获取,不会再次发出sql
        System.out.println("--一级缓存默认是打开的,当你再次查询相同的id时, 不会再发出sql----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);

        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //测试一级缓存,失效
    //关闭sqlSession会话后 , 一级缓存失效
    @Test
    public void level1CacheTest2() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);

        //关闭sqlSession, 一级缓存失效
        if (sqlSession != null) {
            sqlSession.close();
        }

        //因为关闭了sqlSession,所以需要重新初始化sqlSession和 monsterMapper
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        //再次查询id=3的monster
        System.out.println("--如果你关闭了sqlSession,当你再次查询相同的id时, 仍然会发出sql----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);

        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //测试一级缓存,失效
    //如果执行sqlSession.clearCache() , 会导致一级缓存失效
    @Test
    public void level1CacheTest3() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);


        //执行clearCache
        /**
         * @Override
         *   public void clearCache() {
         *     executor.clearLocalCache();
         *   }
         */
        sqlSession.clearCache();

        //再次查询id=3的monster
        System.out.println("--如果你执行sqlSession.clearCache(),当你再次查询相同的id时, 仍然会发出sql----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);


        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //测试一级缓存,失效
    //如果修改了同一个对象 , 会导致一级缓存[对象数据]失效
    @Test
    public void level1CacheTest4() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);


        //如果修改了同一个对象 , 会导致一级缓存[对象数据]失效
        monster.setName("蚂蚱精");
        monsterMapper.updateMonster(monster);

        //再次查询id=3的monster
        System.out.println("--如果你修改了同一个对象,当你再次查询相同的id时, 仍然会发出sql----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);


        if (sqlSession != null) {
            sqlSession.commit();//这里需要commit
            sqlSession.close();
        }
    }

    //测试二级缓存的使用
    @Test
    public void level2CacheTest() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);


        //这里老师关闭sqlSession
        if (sqlSession != null) {
            sqlSession.close();
        }

        //重新获取sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //重新获取了monsterMapper
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        //再次查询id=3的monster
        System.out.println("--虽然前面关闭了sqlSession,因为配置二级缓存, " +
                "当你再次查询相同的id时, 依然不会再发出sql, 而是从二级缓存获取数据----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);

        Monster monster3 = monsterMapper.getMonsterById(3);
        System.out.println("monster3=" + monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //演示二级缓存->一级缓存->DB执行的顺序
    @Test
    public void cacheSeqTest() {

        System.out.println("查询第1次");
        //DB, 会发出SQL, 分析cache hit ratio 0.0
        Monster monster1 = monsterMapper.getMonsterById(3);
        System.out.println(monster1);

        //这里老韩关闭sqlSession, 一级缓存数据没有
        //当我们关闭一级缓存的时候，如果你配置二级缓存,那么一级缓存的数据，会放入到二级缓存
        sqlSession.close();

        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        System.out.println("查询第2次");
        //从二级缓存获取id=3 monster , 就不会发出SQL, 分析cache hit ratio 0.5
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println(monster2);

        System.out.println("查询第3次");
        //从二级缓存获取id=3 monster, 不会发出SQL, 分析cache hit ratio 0.6666
        Monster monster3 = monsterMapper.getMonsterById(3);
        System.out.println(monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");

    }


    //分析缓存执行顺序
    //二级缓存->一级缓存->DB
    //因为二级缓存(数据)是在一级缓存关闭之后才有的
    @Test
    public void cacheSeqTest2() {

        System.out.println("查询第1次");

        //DB , 会发出 SQL, cache hit ratio 0.0
        Monster monster1 = monsterMapper.getMonsterById(3);
        System.out.println(monster1);

        //这里我们没有关闭sqlSession

        System.out.println("查询第2次");
        //从一级缓存获取id=3 , cache hit ratio 0.0, 不会发出SQL
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println(monster2);

        System.out.println("查询第3次");
        //还是从一级缓存获取id=3, cache hit ratio 0.0, 不会发出SQL
        Monster monster3 = monsterMapper.getMonsterById(3);
        System.out.println(monster3);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");

    }

    //测试ehCache级缓存
    @Test
    public void ehCacheTest() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        //会发出SQL, 到db查询
        System.out.println("monster=" + monster);

        //这里老师关闭sqlSession, 一级缓存[数据]失效.=> 将数据放入到二级缓存 (ehcache)
        if (sqlSession != null) {
            sqlSession.close();
        }

        //重新获取sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //重新获取了monsterMapper
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        //再次查询id=3的monster
        System.out.println("--虽然前面关闭了sqlSession,因为配置二级缓存(ehcache), " +
                "当你再次查询相同的id时, 不会再发出sql, 而是从二级缓存(ehcache)获取数据----");
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println("monster2=" + monster2);

        //再次查询id=3的monster, 仍然到二级缓存(ehcache), 获取数据, 不会发出sql
        Monster monster3 = monsterMapper.getMonsterById(3);
        System.out.println("monster3=" + monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }

    }

}
