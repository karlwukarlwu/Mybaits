package org.example;

import org.entity.Monster;
import org.junit.jupiter.api.Test;
import org.mapper.MonsterMapper;
import org.mymybatis.config.MapperBean;
import org.mymybatis.sqlsession.Executor;
import org.mymybatis.sqlsession.MyConfig;
import org.mymybatis.sqlsession.MyExecutor;
import org.mymybatis.sqlsession.MySqlSession;

import java.sql.Connection;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class mymyTest {
    @Test
    public void test() {
        MyConfig myConfig = new MyConfig();
        Connection build = myConfig.build("my_mybatis.xml");
        System.out.println("build = " + build);

    }

    @Test
    public void t2() {
        Executor executor = new MyExecutor();
        Monster monster =
                executor.query("select * from monster where id=?", 1);
        System.out.println("monster-- " + monster);
    }
    @Test
    public void t3() {
        MySqlSession mySqlSession = new MySqlSession();
        Monster monster =
                mySqlSession.selectOne("select * from monster where id=?", 1);
        System.out.println("monster-- " + monster);
    }
    @Test
    public void readMapper() {

        MyConfig myConfig = new MyConfig();
        MapperBean mapperBean = myConfig.readMapper("MonsterMapper.xml");
        System.out.println("mapperBean---" + mapperBean);
        System.out.println("ok~~");
    }

    @Test
    public void getMapper() {
        MySqlSession mySqlSession = new MySqlSession();
        MonsterMapper mapper = mySqlSession.getMapper(MonsterMapper.class);
        System.out.println("mapper= " + mapper.getClass());//mapper是代理对象
//        代理对象这里执行的实际上是invoke
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster--" + monster);
    }

}